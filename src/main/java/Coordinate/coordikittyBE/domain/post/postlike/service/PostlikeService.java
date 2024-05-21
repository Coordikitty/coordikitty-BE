package Coordinate.coordikittyBE.domain.post.postlike.service;

import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostlikeService {
    private final PostRepository postRepository;
    public ResponseEntity<String> like(UUID postId) {
        PostEntity findPost = postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("잘못된 좋아요 요청"));
        findPost.setLikeCount(findPost.getLikeCount()+1);
        return ResponseEntity.ok("좋아요 성공");
    }
    public ResponseEntity<String> dislike(UUID postId) {
        PostEntity findPost = postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("잘못된 좋아요 요청"));
        findPost.setLikeCount(findPost.getLikeCount()-1);
        return ResponseEntity.ok("좋아요 성공");
    }
}
