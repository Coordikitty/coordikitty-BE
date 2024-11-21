package Coordinate.coordikittyBE.domain.post.service;

import Coordinate.coordikittyBE.domain.post.entity.PostImage;
import Coordinate.coordikittyBE.domain.post.repository.PostImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostImageAppender {
    private final PostImageRepository postImageRepository;
    public void append(PostImage postImage) {
        postImageRepository.save(postImage);
    }
}
