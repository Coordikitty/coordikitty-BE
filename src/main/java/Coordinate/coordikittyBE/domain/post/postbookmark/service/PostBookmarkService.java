package Coordinate.coordikittyBE.domain.post.postbookmark.service;

import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.global.common.response.SuccessResponse;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostBookmarkService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public SuccessResponse<?> bookmark(UUID postId, String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new CoordikittyException(ErrorType.POST_NOT_FOUND));

        Optional<History> history = historyRepository.findByUserIdAndPostId(user.getId(), postId);
        if (history.isPresent()) {
            history.get().toggleIsBookmarked();
            return SuccessResponse.from(history.get().getIsBookmarked());
        }

        History newHistory = History.of(user, post);
        newHistory.toggleIsBookmarked();
        historyRepository.save(newHistory);

        return SuccessResponse.from(newHistory.getIsBookmarked());
    }

}
