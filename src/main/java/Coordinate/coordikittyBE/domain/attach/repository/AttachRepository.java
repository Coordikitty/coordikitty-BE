package Coordinate.coordikittyBE.domain.attach.repository;

import Coordinate.coordikittyBE.domain.attach.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachRepository extends JpaRepository<AttachEntity, UUID> {
}
