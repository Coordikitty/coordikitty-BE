package Coordinate.coordikittyBE.domain.history.repository;

import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoryRepository extends CrudRepository<HistoryEntity, UUID> {
    Optional<List<HistoryEntity>> findAllByUserEntityIdAndIsBookmarkedTrue(String userEntityId);
}
