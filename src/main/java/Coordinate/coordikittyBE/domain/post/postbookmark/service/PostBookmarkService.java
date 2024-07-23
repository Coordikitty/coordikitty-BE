package Coordinate.coordikittyBE.domain.post.postbookmark.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PostBookmarkService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public String bookmark(UUID postId, String email) {
        // history 에 bookmark 추가
        // 원래 있던 history 면 북마크 true
        // 없던 history 면 history 생성 후 북마크 true

        User user = userRepository.findByEmail(email).orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(()-> new CoordikittyException(ErrorType.POST_NOT_FOUND));

        History history = historyRepository.findByUserIdAndPostId(user.getId(), postId)
                .orElseGet(() -> History.of(user, post));

        history.toggleBookmarked();
        return "북마크 토글 성공";
    }

    public String deleteBookmark(UUID postId, String email) {
        return "북마크 제거 성공";
    }
}
