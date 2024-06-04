package Coordinate.coordikittyBE.domain.closet.repository;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, UUID> {
    List<Cloth> findAllByUserEmail(String email);

    List<Cloth> findAllByUserEmailAndStyle(String email, Style style);
}
