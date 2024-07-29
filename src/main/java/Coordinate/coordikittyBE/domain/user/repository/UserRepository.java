package Coordinate.coordikittyBE.domain.user.repository;

import Coordinate.coordikittyBE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(UUID id);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}
