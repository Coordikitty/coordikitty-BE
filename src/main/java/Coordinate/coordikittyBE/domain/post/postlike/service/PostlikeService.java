package Coordinate.coordikittyBE.domain.post.postlike.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostlikeService {
    private final PostRepository postRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    public String like(UUID postId, String email) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new RuntimeException("게시글 없음"));
        User user = userRepository.findById(email).orElseThrow(()->new IllegalArgumentException("유저 없음"));
        History history = historyRepository.findByUserEmailAndPostId(email, postId).orElse(null);
        if(history == null) {
            history = History.of(user, post);
            history.setIsLiked(true);
        }
        else{
            history.setIsLiked(true);
        }
        historyRepository.save(history);
        post.like();
        postRepository.save(post);
        return "좋아요 성공";
    }
    public String dislike(UUID postId, String email) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("게시글 없음"));
        History history = historyRepository.findByUserEmailAndPostId(email, postId).orElseThrow(()->new IllegalArgumentException("좋아요 오류"));
        history.setIsLiked(false);
        historyRepository.save(history);
        post.unlike();
        postRepository.save(post);
        return "좋아요 성공";
    }
}
