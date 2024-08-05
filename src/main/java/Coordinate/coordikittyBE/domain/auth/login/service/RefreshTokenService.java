package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.global.util.JwtHelper;
import Coordinate.coordikittyBE.global.util.RedisUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class RefreshTokenService {
    private final RedisUtil redisUtil;
    private final JwtHelper jwtHelper;
    @Value("${prefix.refresh_token_prefix}")
    private String refreshTokenPrefix;
    @Value("${jwt.refresh-expiration}")
    private Long refreshExpiration;

    public void saveRefreshToken(UUID userId, String refreshToken) {
        String key = refreshTokenPrefix + userId.toString();
        redisUtil.save(key, refreshToken);
        redisUtil.saveExpire(userId.toString(), refreshExpiration);
    }

    public boolean getRefreshToken(String token) {
        Claims claims = jwtHelper.parseClaims(token);
        String key = refreshTokenPrefix + claims.get("userId", String.class);
        return redisUtil.get(key);
    }

    public void deleteRefreshToken(String userId) {
        redisUtil.delete(refreshTokenPrefix+userId);
    }

    public String reIssueAccessToken(String refreshToken) {
        Claims claims = jwtHelper.parseClaims(refreshToken);
        return jwtHelper.generateAccessToken(
                claims.getSubject(),
                UUID.fromString(claims.get("userId", String.class))
        );
    }

    public String reIssueRefreshToken(String refreshToken) {
        this.deleteRefreshToken(refreshToken);

        Claims claims = jwtHelper.parseClaims(refreshToken);
        UUID userId = UUID.fromString(claims.get("userId", String.class));

        String newRefreshToken = jwtHelper.generateRefreshToken(
                claims.getSubject(),
                userId
        );

        this.saveRefreshToken(userId, newRefreshToken);
        return newRefreshToken;
    }
}
