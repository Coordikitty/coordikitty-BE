package Coordinate.coordikittyBE.domain.bookmark.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.bookmark.dto.BookmarkResponseDto;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    public List<BookmarkResponseDto> getBookmarksAll(String email) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("유저 이메일 오류"));
        List<History> historyEntities= historyRepository.findAllByUserEmailAndIsBookmarkedTrue(email)
                .orElseThrow(() -> new IllegalArgumentException("기록 오류"));

        List<BookmarkResponseDto> bookmarkResponses = new ArrayList<>();
        for (History history : historyEntities) {
            Post post = history.getPost();
            bookmarkResponses.add(BookmarkResponseDto.fromEntity(post, user, history));
        }
        return bookmarkResponses;
    }
}
