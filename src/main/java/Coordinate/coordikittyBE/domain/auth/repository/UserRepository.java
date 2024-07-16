package Coordinate.coordikittyBE.domain.auth.repository;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);

    // delete from user where email != "jmg@naver.com";
    void deleteByEmailNot(String email);

    //
}
