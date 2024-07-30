package Coordinate.coordikittyBE.global.util.cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    @Value("${jwt.refresh_expiration}")
    private int REFRESH_EXPIRATION;

    public ResponseCookie addRtkCookie(String key, String value) {
        return ResponseCookie.from(key, value)
                .maxAge(REFRESH_EXPIRATION)
                .secure(true)
                .httpOnly(true)
                .path("/")
                .sameSite("None")
                .build();
    }
}