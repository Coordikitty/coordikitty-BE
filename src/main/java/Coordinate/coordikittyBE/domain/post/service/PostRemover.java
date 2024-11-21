package Coordinate.coordikittyBE.domain.post.service;

import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostRemover {
    private final PostRepository postRepository;

    public void removeById(UUID postId) {
        postRepository.deleteById(postId);
    }
}
