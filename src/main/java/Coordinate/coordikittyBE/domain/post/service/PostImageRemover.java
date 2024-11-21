package Coordinate.coordikittyBE.domain.post.service;

import Coordinate.coordikittyBE.domain.post.repository.PostImageRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostImageRemover {
    private final PostImageRepository postImageRepository;

    public void removeAllByPostId(UUID postId) {
        postImageRepository.deleteAllByPostId(postId);
    }
}
