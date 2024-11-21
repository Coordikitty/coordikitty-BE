package Coordinate.coordikittyBE.domain.post.posting.usecase;

import Coordinate.coordikittyBE.domain.attach.entity.Attach;
import Coordinate.coordikittyBE.domain.attach.repository.AttachRepository;
import Coordinate.coordikittyBE.domain.attach.service.AttachReader;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.service.HistoryAppender;
import Coordinate.coordikittyBE.domain.history.service.HistoryReader;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.entity.PostImage;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUpdateRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostUpdateResponseDto;
import Coordinate.coordikittyBE.domain.post.service.PostAppender;
import Coordinate.coordikittyBE.domain.post.service.PostImageAppender;
import Coordinate.coordikittyBE.domain.post.service.PostImageReader;
import Coordinate.coordikittyBE.domain.post.service.PostImageRemover;
import Coordinate.coordikittyBE.domain.post.service.PostManager;
import Coordinate.coordikittyBE.domain.post.service.PostReader;
import Coordinate.coordikittyBE.domain.post.service.PostRemover;
import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.service.UserReader;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.global.common.annotation.UseCase;
import Coordinate.coordikittyBE.global.util.FirebaseHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@UseCase
@RequiredArgsConstructor
public class PostingUseCase {
    private final PostImageReader postImageReader;
    private final UserReader userReader;
    private final HistoryReader historyReader;
    private final PostReader postReader;
    private final AttachReader attachReader;
    private final FirebaseHelper firebaseHelper;
    private final PostImageRemover postImageRemover;
    private final PostManager postManager;
    private final PostAppender postAppender;
    private final PostImageAppender postImageAppender;
    private final HistoryAppender historyAppender;
    private final PostRemover postRemover;
    private final ClothRepository clothRepository;
    private final AttachRepository attachRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> readAllPosts(String email) {
        return postReader.findAllByOrderByCreatedAtDesc().stream()
            .map(post -> generateResponse(post, email))
            .toList();
    }

    @Transactional
    public PostUpdateResponseDto update(UUID postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postReader.findById(postId);
        List<Attach> attaches = attachReader.readAllByPostId(postId);

        firebaseHelper.deletePostImage(postId);

        postImageRemover.removeAllByPostId(postId);

        postManager.updatePost(postUpdateRequestDto, attaches, post, postId);

        return PostUpdateResponseDto.from(attaches);
    }

    @Transactional(readOnly = true)
    public PostResponseDto readById(UUID postId, String email) {
        Post post = postReader.findById(postId);

        return generateResponse(post, email);
    }

    @Transactional
    public PostResponseDto upload(PostUploadRequestDto postUploadRequestDto, List<MultipartFile> images, String email) {
        User user = userReader.readByEmail(email);
        Post post = postAppender.append(postUploadRequestDto, user);

        List<String> postImageUrls = images.stream()
            .map(image -> {
                String imageUrl = firebaseHelper.uploadPostImage(image, post.getId());
                PostImage postImage = PostImage.from(imageUrl, post);
                postImageAppender.append(postImage);
                post.addImageUrl(postImage);
                return postImage.getImageUrl();
            }).toList();

        History history = historyAppender.append(user, post);
        post.getAttaches().addAll(createAttaches(postUploadRequestDto.clothIds(), post));

        return PostResponseDto.fromEntity(post, postImageUrls, history);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> readByEmail(String email) {
        return postReader.findAllByEmailOrderByCreatedAtDesc(email).stream()
            .map(post -> generateResponse(post, email)).toList();
    }

    @Transactional
    public void delete(UUID postId) {
        postRemover.removeById(postId);
        firebaseHelper.deletePostImage(postId);
    }

    private PostResponseDto generateResponse(Post post, String email) {
        List<String> postImages = postImageReader.readAllByPostId(post.getId())
            .stream()
            .map(PostImage::getImageUrl)
            .toList();
        User user = userReader.readByEmail(email);
        History history = historyReader.findByUserIdAndPostId(user, post);
        return PostResponseDto.fromEntity(post, postImages, history);
    }

    private List<Attach> createAttaches(List<UUID> clothIds, Post post) {
        return clothIds.stream()
            .map(clothId -> {
            Cloth cloth = clothRepository.findById(clothId)
                .orElseThrow(() -> new CoordikittyException(ErrorType.CLOTH_NOT_FOUND));
            Attach attach = Attach.of(cloth, post);
            attachRepository.save(attach);
            return attach;
        }).toList();
    }
}
