package Coordinate.coordikittyBE.domain.post.posting.service;


import Coordinate.coordikittyBE.domain.attach.AttachEntity;
import Coordinate.coordikittyBE.domain.attach.repository.AttachRepository;
import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
import Coordinate.coordikittyBE.domain.bookmark.repository.BookmarkRepository;
import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUpdateRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostlistResponseDto;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostingService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final PostListBuilder postListBuilder;
    private final ClothRepository clothRepository;
    private final AttachRepository attachRepository;
    private final BookmarkRepository bookmarkRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    public List<PostlistResponseDto> getPosts(int page, UserDetails userDetails) {
        // 페이지 번호에 맞는 게시글 반환
        // 반환 게시글 리스트 = 최신 + 인기 + 추천

        List<PostlistResponseDto> posts = new ArrayList<>();
        List<PostEntity> postEntities = postRepository.findAll();
        String email = userDetails.getUsername();

        // 최신
        Comparator<PostEntity> comparatorNew = Comparator.comparing(PostEntity::getCreatedAt).reversed();
        postListBuilder.listBuilder(comparatorNew, posts, postEntities, email);

        // 인기
        Comparator<PostEntity> comparatorPopular = Comparator.comparing(PostEntity::getLikeCount).reversed();
        postListBuilder.listBuilder(comparatorPopular, posts, postEntities, email);

        // 추천

        return posts;
    }

    public PostResponseDto findById(UUID postId) {
        Optional<PostEntity> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return postConverter.toDto(post.get());
        }
        else{
            throw new RuntimeException("게시글 없음");
        }
    }

    public void delete(UUID postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public void upload(PostUploadRequestDto postUploadRequestDto, String email) {
        UserEntity user = userRepository.findById(email).orElse(null);
        PostEntity post = postConverter.fromDto(postUploadRequestDto);
        postRepository.save(post);

        BookmarkEntity bookmark = BookmarkEntity.of(user, post);
        post.getBookmarks().add(bookmark);
        bookmarkRepository.save(bookmark);

        HistoryEntity history = HistoryEntity.of(user, post);
        historyRepository.save(history);
        post.getHistorys().add(history);
        post.getAttaches().addAll(findAttaches(postUploadRequestDto, post));
        postRepository.save(post);

    }

    public void update(UUID postId, PostUpdateRequestDto postUpdateRequestDto) {
        Optional<PostEntity> findPost = postRepository.findById(postId);
        if (findPost.isPresent()) {
            findPost.get().update(postUpdateRequestDto);
        }
        else{
            throw new RuntimeException("게시글 없음");
        }
    }

    private List<AttachEntity> findAttaches(PostUploadRequestDto postUploadRequestDto, PostEntity post) {
        List<AttachEntity> attaches = new ArrayList<>();
        for (UUID clothId : postUploadRequestDto.getClothIds()) {
            ClothEntity cloth = clothRepository.findById(clothId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 옷 없음."));
            AttachEntity attach = AttachEntity.of(cloth, post);
            attachRepository.save(attach);
            attaches.add(attach);
        }
        return attaches;
    }
}
