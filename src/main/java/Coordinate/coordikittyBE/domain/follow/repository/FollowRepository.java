package Coordinate.coordikittyBE.domain.follow.repository;

import Coordinate.coordikittyBE.domain.follow.enity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {
}
