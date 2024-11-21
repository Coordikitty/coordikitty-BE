package Coordinate.coordikittyBE.domain.history.service;

import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
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
}
