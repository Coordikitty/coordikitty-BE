package Coordinate.coordikittyBE.domain.post.service;

import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReader {
    private final PostRepository postRepository;

    public Post readById(UUID postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new CoordikittyException(ErrorType.POST_NOT_FOUND));
    }
}
