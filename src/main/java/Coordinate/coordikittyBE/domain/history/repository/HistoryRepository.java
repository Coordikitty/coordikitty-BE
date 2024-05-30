package Coordinate.coordikittyBE.domain.history.repository;

import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<HistoryEntity, UUID> {
    @Query("SELECT h FROM history h WHERE h.userEntity = :userId and h.isBookmarked = true")
    Optional<List<HistoryEntity>> findAllByUserIdandIsBookmaredTrue(String userId);

    @Query("select h from history h where h.postEntity.postId = :postId and h.userEntity.email = :userId")
    Optional<List<HistoryEntity>> findAllByPostIdandUserId(UUID postId, String userId);
}
