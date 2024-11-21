package Coordinate.coordikittyBE.domain.post.postlike.service;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.global.common.response.SuccessResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public SuccessResponse<?> like(UUID postId, String email) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CoordikittyException(ErrorType.POST_NOT_FOUND));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        Optional<History> history = historyRepository.findByUserIdAndPostId(user.getId(), postId);
        if (history.isPresent()) {
            history.get().toggleLike();
            upOrDownPostLikeCount(history.get(), post);
            return SuccessResponse.from(post.getLikeCount());
        }

        History newHistory = History.of(user, post);
        newHistory.toggleLike();
        upOrDownPostLikeCount(newHistory, post);
        historyRepository.save(newHistory);

        return SuccessResponse.from(post.getLikeCount());
    }

    private void upOrDownPostLikeCount(History history, Post post) {
        if(history.getIsLiked()){
            post.like();
            return;
        }
        post.unlike();
    }

}
