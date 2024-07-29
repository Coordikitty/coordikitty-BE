package Coordinate.coordikittyBE.domain.post.postbookmark.service;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.global.common.response.SuccessResponse;
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

    public SuccessResponse<String> bookmark(UUID postId, String email) {
        // history 에 bookmark 추가
        // 원래 있던 history 면 북마크 true
        // 없던 history 면 history 생성 후 북마크 true

        User user = userRepository.findByEmail(email).orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(()-> new CoordikittyException(ErrorType.POST_NOT_FOUND));

        History history = historyRepository.findByUserIdAndPostId(user.getId(), postId)
                .orElseGet(() -> History.of(user, post));

        history.toggleBookmarked();
        if(history.getIsLiked()){
            return SuccessResponse.from("북마크 추가 성공");
        }
       return SuccessResponse.from("북마크 해제 성공");
    }

}
