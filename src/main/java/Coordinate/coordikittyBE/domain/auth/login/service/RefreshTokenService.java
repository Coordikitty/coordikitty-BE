package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken).orElse(null);
    }
    public RefreshToken findByUserId(String email) {
        return refreshTokenRepository.findByUserId(email).orElse(null);
    }

    public void save(RefreshToken refreshTokenInfo) {
        refreshTokenRepository.save(refreshTokenInfo);
    }

    public void saveRefreshToken(String email, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(email)
                .map(entity->entity.update(newRefreshToken))
                .orElse(new RefreshToken(email, newRefreshToken));
        refreshTokenRepository.save(refreshToken);
    }
}
