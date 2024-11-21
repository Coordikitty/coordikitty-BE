package Coordinate.coordikittyBE.domain.post.service;

import Coordinate.coordikittyBE.domain.post.entity.PostImage;
import Coordinate.coordikittyBE.domain.post.repository.PostImageRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostImageReader {
    private final PostImageRepository postImageRepository;

    public List<PostImage> readAllByPostId(UUID postId) {
        return postImageRepository.findAllByPostId(postId);
    }
}
