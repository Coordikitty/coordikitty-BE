package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(String email, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(email)
                .map(entity->entity.update(newRefreshToken))
                .orElse(RefreshToken.of(email, newRefreshToken));
        refreshTokenRepository.save(refreshToken);
    }

}
