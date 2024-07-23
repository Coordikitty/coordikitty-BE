package Coordinate.coordikittyBE.domain.post.posting.service;


import Coordinate.coordikittyBE.domain.attach.entity.Attach;
import Coordinate.coordikittyBE.domain.attach.repository.AttachRepository;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.entity.PostImage;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUpdateRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostUpdateResponseDto;
import Coordinate.coordikittyBE.domain.post.repository.PostDao;
import Coordinate.coordikittyBE.domain.post.repository.PostImageRepository;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;

import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PostingService {
    private final PostRepository postRepository;
    private final ClothRepository clothRepository;
    private final AttachRepository attachRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final PostDao postDao;
    private final PostImageRepository postImageRepository;

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::findAllImageUrlByPostId)
                .toList();
    }

    public PostResponseDto findById(UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new CoordikittyException(ErrorType.POST_NOT_FOUND));
        return findAllImageUrlByPostId(post);
    }

    public void delete(UUID postId) {
        postRepository.deleteById(postId);
        postDao.delete(postId);
    }

    public PostResponseDto upload(PostUploadRequestDto postUploadRequestDto, List<MultipartFile> images, String email) {
        User user = userRepository.findById(email)
                .orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        Post post = PostUploadRequestDto.toEntity(postUploadRequestDto, user);
        List<String> postImageUrls = new ArrayList<>();
        images.stream()
                .map(image -> {
                    String imageUrl = postDao.upload(image, post.getId());
                    PostImage postImage = PostImage.from(imageUrl, post);
                    post.addImageUrl(postImage);
                    postImageRepository.save(postImage);
                    return imageUrl;
                })
                .forEach(postImageUrls::add);
        postRepository.save(post);

        History history = History.of(user, post);
        historyRepository.save(history);
        post.getHistorys().add(history);
        post.getAttaches().addAll(createAttaches(postUploadRequestDto.clothIds(), post));
        return PostResponseDto.fromEntity(post, postImageUrls, history);
    }

    public PostUpdateResponseDto update(UUID postId, PostUpdateRequestDto postUpdateRequestDto) {
        //post 찾고
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CoordikittyException(ErrorType.POST_NOT_FOUND));

        //attach 지우고
        attachRepository.deleteAllByPostId(postId);

        //기존 이미지 지우고(firebase)
        postDao.delete(postId);

        //postImgs 변경
        postImageRepository.deleteAllByPostId(postId);

        //새 이미지 업로드(firebase)
        List<PostImage> postImages = new ArrayList<>();
        postUpdateRequestDto.postImgs().forEach(img -> postImages.add(PostImage.from(postDao.upload(img, postId), post)));

        //새 attach 생성
        List<Attach> attaches = createAttaches(postUpdateRequestDto.clothIds(), post);
        post.update(postUpdateRequestDto, attaches, postImages);
        return PostUpdateResponseDto.from(attaches);
    }

    private List<Attach> createAttaches(List<UUID> clothIds, Post post) {
        List<Attach> attaches = new ArrayList<>();
        clothIds.forEach(clothId -> {
            Cloth cloth = clothRepository.findById(clothId)
                    .orElseThrow(()-> new CoordikittyException(ErrorType.CLOTH_NOT_FOUND));
            Attach attach = Attach.of(cloth, post);
            attachRepository.save(attach);
            attaches.add(attach);
        });
//        for (UUID clothId : clothIds) {
//            Cloth cloth = clothRepository.findById(clothId)
//                    .orElseThrow(() -> new CoordikittyException(ErrorType.CLOTH_NOT_FOUND));
//            Attach attach = Attach.of(cloth, post);
//            attachRepository.save(attach);
//            attaches.add(attach);
//        }
        return attaches;
    }

    private PostResponseDto findAllImageUrlByPostId(Post post) {
        List<String> postImages = postImageRepository.findAllByPostId(post.getId())
                .stream()
                .map(PostImage::getImageUrl)
                .toList();
        History history = historyRepository.findByUserIdAndPostId(post.getUser().getId(), post.getId())
                .orElseThrow(()-> new CoordikittyException(ErrorType.HISTORY_NOT_FOUND));
        return PostResponseDto.fromEntity(post, postImages, history);
    }
}
