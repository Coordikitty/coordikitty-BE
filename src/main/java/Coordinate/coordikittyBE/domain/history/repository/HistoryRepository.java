package Coordinate.coordikittyBE.domain.history.repository;

import Coordinate.coordikittyBE.domain.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    Optional<List<History>> findAllByUserEmailAndIsBookmarkedTrue(String email);
    List<History> findAllByPostIdAndUserEmail(UUID postId, String userId);

    Optional<History> findByUserEmailAndPostId(String email, UUID postId);
}
