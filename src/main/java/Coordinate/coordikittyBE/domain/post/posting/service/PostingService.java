package Coordinate.coordikittyBE.domain.post.posting.service;


import Coordinate.coordikittyBE.domain.attach.entity.Attach;
import Coordinate.coordikittyBE.domain.attach.repository.AttachRepository;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.bookmark.entity.Bookmark;
import Coordinate.coordikittyBE.domain.bookmark.repository.BookmarkRepository;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUpdateRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostUpdateResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostlistResponseDto;
import Coordinate.coordikittyBE.domain.post.repository.PostDao;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostingService {
    private final PostRepository postRepository;
    private final ClothRepository clothRepository;
    private final AttachRepository attachRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final PostDao postDao;

    public List<PostlistResponseDto> getPostsLoggedIn(UserDetails userDetails) {

        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        return new ArrayList<>(posts.stream()
                .map(post -> {
                    History history = historyRepository.findByUserEmailAndPostId(post.getUser().getEmail(), post.getId()).orElseThrow();
                    return PostlistResponseDto.fromEntity(post, history);
                }).toList());
    }

    public List<PostlistResponseDto> getPostsUnLoggedIn() {
        List<Post> postEntities = postRepository.findAllByOrderByCreatedAtDesc();
        return postEntities.stream()
                .map(post-> {
                        History history = historyRepository.findByUserEmailAndPostId(post.getUser().getEmail(), post.getId()).orElseThrow();
                        return PostlistResponseDto.fromEntity(post, history);
                })
                .collect(Collectors.toList());
    }

    public PostResponseDto findById(UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("게시글 없음."));
        History history = historyRepository.findByUserEmailAndPostId(post.getUser().getEmail(), post.getId()).orElseThrow();
        return PostResponseDto.fromEntity(post, history);
    }

    public void delete(UUID postId)throws IllegalArgumentException {
        postRepository.deleteById(postId);
        postDao.delete(postId);
    }

    @Transactional
    public PostResponseDto upload(PostUploadRequestDto postUploadRequestDto, List<MultipartFile> images, String email) throws IOException {
        User user = userRepository.findById(email).orElse(null);
        Post post = PostUploadRequestDto.toEntity(postUploadRequestDto, user);
        for (MultipartFile image : images) {
            String imageUrl = postDao.upload(image, post.getId());
            post.addImageUrl(imageUrl);
        }
        postRepository.save(post);

                History history = History.of(user, post);
        historyRepository.save(history);
        post.getHistorys().add(history);
        post.getAttaches().addAll(createAttaches(postUploadRequestDto.getClothIds(), post));
        postRepository.save(post);
        return PostResponseDto.fromEntity(post, history);
    }

    @Transactional
    public PostUpdateResponseDto update(UUID postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시글 없음"));
        attachRepository.deleteAllByPostId(postId);

        List<Attach> attaches = createAttaches(postUpdateRequestDto.getClothIds(), post);
        post.update(postUpdateRequestDto, attaches);
        return PostUpdateResponseDto.to(attaches);
    }

    private List<Attach> createAttaches(List<UUID> clothIds, Post post) {
        List<Attach> attaches = new ArrayList<>();
        for (UUID clothId : clothIds) {
            System.out.println(clothId);
            Cloth cloth = clothRepository.findById(clothId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 옷 없음."));
            Attach attach = Attach.of(cloth, post);
            attachRepository.save(attach);
            attaches.add(attach);
        }
        return attaches;
    }

}
