package Coordinate.coordikittyBE.domain.auth.repository;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(UUID userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    void deleteByUserId(UUID userId);
}
