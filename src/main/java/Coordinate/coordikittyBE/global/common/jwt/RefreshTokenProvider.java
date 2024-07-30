package Coordinate.coordikittyBE.global.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.UUID;

@Component
@Qualifier("refreshTokenProvider")
public class RefreshTokenProvider implements JwtTokenProvider{
    private final SecretKey secretKey;
    private final Duration refreshTokenDuration;
    private final String issuer;

    public RefreshTokenProvider(
            @Value("${jwt.secret_key}") String secretKey,
            @Value("${jwt.access_expiration}") Duration accessTokenExpiration,
            @Value("${jwt.issuer}") String issuer
    ){
        this.secretKey = new SecretKeySpec(secretKey.getBytes(), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.refreshTokenDuration = accessTokenExpiration;
        this.issuer = issuer;
    }

    @Override
    public String generateToken(String email, UUID userId) {
        return Jwts.builder()
                .subject(email)
                .issuer(issuer)
                .expiration(createExpire(refreshTokenDuration.toMillis()))
                .claim("userId", userId)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Claims parseClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
