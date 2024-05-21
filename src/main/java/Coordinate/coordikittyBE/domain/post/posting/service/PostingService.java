package Coordinate.coordikittyBE.domain.post.posting.service;


import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUpdateRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostingService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
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
