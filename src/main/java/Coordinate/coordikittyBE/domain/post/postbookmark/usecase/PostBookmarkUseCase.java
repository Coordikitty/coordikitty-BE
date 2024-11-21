package Coordinate.coordikittyBE.domain.post.postbookmark.usecase;

import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.service.HistoryAppender;
import Coordinate.coordikittyBE.domain.history.service.HistoryManager;
import Coordinate.coordikittyBE.domain.history.service.HistoryReader;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.entity.PostImage;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.service.PostImageReader;
import Coordinate.coordikittyBE.domain.post.service.PostReader;
import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.service.UserReader;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.global.common.annotation.UseCase;
import java.util.List;
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
    private final PostImageReader postImageReader;
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

    @Transactional
    public List<PostResponseDto> findByEmailAndBookmark(String email) {
        return postReader.readAllByEmailAndIsBookmarked(email, true).stream()
            .map(post -> generateResponse(post, email)).toList();
    }

    private PostResponseDto generateResponse(Post post, String email) {
        List<String> postImages = postImageReader.readAllByPostId(post.getId())
            .stream()
            .map(PostImage::getImageUrl)
            .toList();
        User user = userReader.readByEmail(email);
        History history = historyReader.findByUserIdAndPostId(user, post);
        return PostResponseDto.fromEntity(post, postImages, history);
    }
}
