package Coordinate.coordikittyBE.domain.attach.repository;

import Coordinate.coordikittyBE.domain.attach.Attach;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttachRepository extends JpaRepository<Attach, UUID> {
    void deleteAllByPostId(UUID postId);
}
