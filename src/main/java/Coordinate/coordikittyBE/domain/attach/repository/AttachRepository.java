package Coordinate.coordikittyBE.domain.attach.repository;

import Coordinate.coordikittyBE.domain.attach.entity.Attach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttachRepository extends JpaRepository<Attach, UUID> {
    void deleteAllByPostId(UUID postId);
    void deleteAllByClothId(UUID clothId);

    List<Attach> findAllByPostId(UUID postId);
}
