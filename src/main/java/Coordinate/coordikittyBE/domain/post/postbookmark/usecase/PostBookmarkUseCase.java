package Coordinate.coordikittyBE.domain.post.postbookmark.usecase;

import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.service.HistoryAppender;
import Coordinate.coordikittyBE.domain.history.service.HistoryManager;
import Coordinate.coordikittyBE.domain.history.service.HistoryReader;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.service.PostReader;
import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.service.UserReader;
import Coordinate.coordikittyBE.global.common.annotation.UseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class PostBookmarkUseCase {
    private final UserReader userReader;
    private final PostReader postReader;
    private final HistoryReader historyReader;
    private final HistoryAppender historyAppender;
    private final HistoryManager historyManager;
    @Transactional
    public boolean toggleBookmark(UUID postId, String email) {
        User user = userReader.readByEmail(email);
        Post post = postReader.readById(postId);
        History history = historyReader.readByUserIdAndPostId(user.getId(), postId);

        if(history == null) {
            history = historyAppender.append(user, post);
        }

        historyManager.toggleIsBookmarked(history);

        return history.getIsBookmarked();
    }
}
