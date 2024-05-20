package Coordinate.coordikittyBE.domain.closet.repository;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClothRepository extends JpaRepository<ClothEntity, UUID> {
    List<ClothEntity> findAllByUserEntity(UserEntity userEntity);
}
