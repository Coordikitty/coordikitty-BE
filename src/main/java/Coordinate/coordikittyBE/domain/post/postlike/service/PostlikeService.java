package Coordinate.coordikittyBE.domain.post.postlike.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PostlikeService {
    private final PostRepository postRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    public String like(UUID postId, String email) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CoordikittyException(ErrorType.POST_NOT_FOUND));
        User user = userRepository.findById(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        History history = historyRepository.findByUserEmailAndPostId(email, postId)
                .orElseGet(() -> History.of(user, post));

        history.liked();
        historyRepository.save(history);
        post.like();
        return "좋아요 성공";
    }

    public String dislike(UUID postId, String email) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CoordikittyException(ErrorType.POST_NOT_FOUND));
        History history = historyRepository.findByUserEmailAndPostId(email, postId)
                .orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        history.unLiked();
        post.unlike();
        return "좋아요 성공";
    }
}
