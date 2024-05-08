package Coordinate.coordikittyBE.domain.auth.repository;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserEntity, String> {
}
