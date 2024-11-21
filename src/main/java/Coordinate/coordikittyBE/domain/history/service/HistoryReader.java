package Coordinate.coordikittyBE.domain.history.service;

import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.user.entity.User;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryReader {
    private final HistoryRepository historyRepository;

    public History readByUserIdAndPostId(UUID userId, UUID postId) {
        return historyRepository.findByUserIdAndPostId(userId, postId).orElse(null);
    }

    public History findByUserIdAndPostId(User user, Post post) {
        return historyRepository.findByUserIdAndPostId(user.getId(), post.getId())
            .orElseGet(()-> historyRepository.save(History.of(user, post)));
    }
}
