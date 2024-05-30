package Coordinate.coordikittyBE.domain.post.posting.service;


import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
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

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostingService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final HistoryRepository historyRepository;
    private final PostListBuilder postListBuilder;

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

    public void upload(PostUploadRequestDto postUploadRequestDto) {
        PostEntity post = PostEntity.builder()
                .postId(UUID.randomUUID())
                .likeCount(0)
                .content(postUploadRequestDto.getContent())
                .style(postUploadRequestDto.getStyle())
                .createdAt(LocalDate.now())
                .modifiedAt(null)
                .bookmarks(null)//수정필요
                .attaches(null)
                .historys(null)
                .build();
        post.getBookmarks().add(
                BookmarkEntity.builder()
                    .bookmarkId(UUID.randomUUID())
                    .userEntity(new UserEntity())
                    .postEntity(post).build());
        post.getHistorys().add(HistoryEntity.builder()
            .historyId(UUID.randomUUID())
            .postEntity(post)
            .userEntity(new UserEntity())
            .build());
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
}
