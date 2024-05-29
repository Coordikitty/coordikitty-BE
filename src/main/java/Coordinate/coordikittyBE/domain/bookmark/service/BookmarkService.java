package Coordinate.coordikittyBE.domain.bookmark.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.bookmark.dto.BookmarkResponseDto;
import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostResponseDto;
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
        UserEntity user = userRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("유저 이메일 오류"));
        List<HistoryEntity> historyEntities= historyRepository.findAllByUserEntityIdAndIsBookmarkedTrue(email)
                .orElseThrow(() -> new IllegalArgumentException("기록 오류"));

        List<BookmarkResponseDto> bookmarkResponses = new ArrayList<>();
        for (HistoryEntity history : historyEntities) {
            PostEntity post = history.getPostEntity();
            bookmarkResponses.add(BookmarkResponseDto.fromEntity(post, user, history));
        }
        return bookmarkResponses;
    }
}
