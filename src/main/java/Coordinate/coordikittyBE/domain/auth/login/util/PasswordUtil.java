package Coordinate.coordikittyBE.domain.auth.login.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class PasswordUtil {

    public static String encodePassWord(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean comparePassWord(String password, String encodedPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(password, encodedPassword);
    }
}
