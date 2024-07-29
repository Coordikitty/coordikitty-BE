package Coordinate.coordikittyBE.security.jwt;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.service.UserDetailServiceImpl;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
    private final long access_expiration;
    private final long refresh_expiration;
    private final String issuer;
    private final UserRepository userRepository;
    private final UserDetailServiceImpl userDetailService;

    public JwtTokenProvider(
            @Value("${jwt.secret_key}") final String SECRET_KEY,
            @Value("${jwt.access_expiration}") long access_expiration,
            @Value("${jwt.refresh_expiration}") long refresh_expiration,
            @Value("${jwt.issuer}") String issuer,
            UserRepository userRepository,
            UserDetailServiceImpl userDetailService
    ) {
        this.secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.access_expiration = access_expiration;
        this.refresh_expiration = refresh_expiration;
        this.issuer = issuer;
        this.userRepository = userRepository;
        this.userDetailService = userDetailService;
    }


    public TokenDto generateToken(User user) {
        // Access Token 생성
        String accessToken = Jwts.builder()
                .subject(user.getUsername())
                .issuer(issuer)
                .expiration(new Date(System.currentTimeMillis() + access_expiration))
                .claim("auth", user.getId())//UUID pk로 수정 필요
                .signWith(secretKey)
                .compact();
        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .subject(user.getUsername())
                .issuer(issuer)
                .expiration(new Date(System.currentTimeMillis() + refresh_expiration))
                .signWith(secretKey)
                .compact();
        return TokenDto.of(accessToken, refreshToken);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = parseClaims(token);

            if (userRepository.findById(UUID.fromString(claims.get("auth", String.class))).isEmpty()) {
                throw new CoordikittyException(ErrorType.MEMBER_NOT_FOUND);
            }
            if(claims.getExpiration().before(new Date())){
                throw new CoordikittyException(ErrorType.INVALID_TOKEN);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        String email = claims.getSubject();

        UserDetails userDetails = userDetailService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, accessToken, userDetails.getAuthorities());
    }

    private Claims parseClaims(String accessToken) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();
    }

}