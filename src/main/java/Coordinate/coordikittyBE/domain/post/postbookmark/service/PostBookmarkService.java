package Coordinate.coordikittyBE.domain.post.postbookmark.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostBookmarkService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public String addBookmark(UUID postId, String email) {
        User user = userRepository.findById(email).orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(()-> new CoordikittyException(ErrorType.POST_NOT_FOUND));

        return "북마크 생성 성공";

    }

    public String deleteBookmark(UUID postId, String email) {
        return "북마크 제거 성공";
    }
}
