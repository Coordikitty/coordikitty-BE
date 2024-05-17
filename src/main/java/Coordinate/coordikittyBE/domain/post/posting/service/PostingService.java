package Coordinate.coordikittyBE.domain.post.posting.service;


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
        PostEntity entity = PostEntity.builder()
                .postId(UUID.randomUUID())
                .likeCount(0)
                .content(postUploadRequestDto.getContent())
                .style(postUploadRequestDto.getStyle())
                .createdAt(LocalDate.now())
                .modifiedAt(null)
                .bookmarkEntities(null)
                .attachEntities(null)
                .historyEntities(null)
                .build();
        postRepository.save(entity);

    }

    public void update(UUID postId, PostUpdateRequestDto postUpdateRequestDto) {
        Optional<PostEntity> findPost = postRepository.findById(postId);
        if (findPost.isPresent()) {
            PostEntity post = findPost.get().update(postUpdateRequestDto);
            postRepository.save(post);
        }
        else{
            throw new RuntimeException("게시글 없음");
        }
    }
}
