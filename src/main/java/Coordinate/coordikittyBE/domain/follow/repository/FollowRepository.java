package Coordinate.coordikittyBE.domain.follow.repository;

import Coordinate.coordikittyBE.domain.follow.enity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FollowRepository extends JpaRepository<FollowEntity, UUID> {
}
