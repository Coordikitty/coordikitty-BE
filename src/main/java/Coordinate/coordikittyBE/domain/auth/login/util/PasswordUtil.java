package Coordinate.coordikittyBE.domain.auth.login.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class PasswordUtil {
    private static BCryptPasswordEncoder encoder;

    public static String encodePassWord(String password){
        return encoder.encode(password);
    }

    public static boolean comparePassWord(String password, String encodedPassword){
        return encoder.matches(password, encodedPassword);
    }
}
