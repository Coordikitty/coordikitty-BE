package Coordinate.coordikittyBE.domain.history.service;

import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryAppender {
    private final HistoryRepository historyRepository;

    public History append(User user, Post post) {
        History history = History.of(user, post);
        historyRepository.save(history);
        post.getHistorys().add(history);
        return history;
    }
}
