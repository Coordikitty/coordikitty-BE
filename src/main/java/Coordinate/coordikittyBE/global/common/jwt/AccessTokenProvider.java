package Coordinate.coordikittyBE.global.common.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.UUID;

@Component
@Primary
@Qualifier("accessTokenProvider")
public class AccessTokenProvider implements JwtTokenProvider{
    private final SecretKey secretKey;
    private final Duration accessTokenDuration;
    private final String issuer;

    public AccessTokenProvider(
            @Value("${jwt.secret_key}") String secretKey,
            @Value("${jwt.access_expiration}") Duration accessTokenExpiration,
            @Value("${jwt.issuer}") String issuer
    ){
        this.secretKey = new SecretKeySpec(secretKey.getBytes(), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.accessTokenDuration = accessTokenExpiration;
        this.issuer = issuer;
    }

    @Override
    public String generateToken(String email, UUID userId) {
        return Jwts.builder()
                .subject(email)
                .issuer(issuer)
                .expiration(createExpire(accessTokenDuration.toMillis()))
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
