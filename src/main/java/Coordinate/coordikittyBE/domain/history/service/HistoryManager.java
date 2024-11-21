package Coordinate.coordikittyBE.domain.history.service;

import Coordinate.coordikittyBE.domain.history.entity.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryManager {
    public void toggleIsBookmarked(History history) {
        history.toggleIsBookmarked();
    }

}
