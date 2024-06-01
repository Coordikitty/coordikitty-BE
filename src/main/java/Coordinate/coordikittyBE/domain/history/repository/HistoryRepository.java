package Coordinate.coordikittyBE.domain.history.repository;

import Coordinate.coordikittyBE.domain.history.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    Optional<List<History>> findAllByUserEmailAndIsBookmarkedTrue(String email);
    List<History> findAllByPostIdAndUserEmail(UUID postId, String userId);
}
