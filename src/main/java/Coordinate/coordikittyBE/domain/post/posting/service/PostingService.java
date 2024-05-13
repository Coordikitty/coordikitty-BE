package Coordinate.coordikittyBE.domain.post.posting.service;

import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
