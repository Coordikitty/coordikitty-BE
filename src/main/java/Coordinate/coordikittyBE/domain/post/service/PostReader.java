package Coordinate.coordikittyBE.domain.post.service;

import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import java.util.Arrays;
import java.util.List;
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

    public List<Post> readAllByEmailAndIsBookmarked(String email, boolean isBookmarked) {
        return postRepository.findAllByEmailAndIsBookmarkedOrderByCreatedAtDesc(email, isBookmarked);
    }

    public List<Post> findAllByOrderByCreatedAtDesc() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post findById(UUID postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new CoordikittyException(ErrorType.POST_NOT_FOUND));
    }

    public List<Post> findAllByEmailOrderByCreatedAtDesc(String email) {
        return postRepository.findAllByUserEmailOrderByCreatedAtDesc(email);
    }
}
