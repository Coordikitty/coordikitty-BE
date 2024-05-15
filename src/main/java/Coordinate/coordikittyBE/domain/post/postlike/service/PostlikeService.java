package Coordinate.coordikittyBE.domain.post.postlike.service;

import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostlikeService {
    PostRepository postRepository;
    public void like(UUID postId) {
        var findPost = postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("게시글 없음"));
        findPost.setLikeCount(findPost.getLikeCount()+1);
    }
    public void dislike(UUID postId) {
        var findPost = postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("게시글 없음"));
        findPost.setLikeCount(findPost.getLikeCount()-1);
    }
}
