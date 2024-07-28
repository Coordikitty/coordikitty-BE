package Coordinate.coordikittyBE.domain.history.repository;

import Coordinate.coordikittyBE.domain.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    Optional<List<History>> findAllByUserIdAndIsBookmarkedTrue(UUID userId);
    List<History> findAllByPostIdAndUserId(UUID postId, UUID userId);

    Optional<History> findByUserIdAndPostId(UUID userId, UUID postId);

    void deleteAllByPostId(UUID postId);
    void deleteAllByUserId(UUID user_id);
}
