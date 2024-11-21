package Coordinate.coordikittyBE.domain.post.service;

import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostAppender {
    private final PostRepository postRepository;

    public Post append(PostUploadRequestDto postUploadRequestDto, User user) {
        Post post = PostUploadRequestDto.toEntity(postUploadRequestDto, user);
        return postRepository.save(post);
    }
}
