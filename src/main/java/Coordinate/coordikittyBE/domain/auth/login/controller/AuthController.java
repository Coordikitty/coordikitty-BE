package Coordinate.coordikittyBE.domain.auth.login.controller;

import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginInfoResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.request.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.request.SocialLoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.service.AuthService;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.global.util.cookie.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final CookieUtil cookieUtil;

    @PostMapping("/token")
    public ResponseEntity<?> createNewAccessToken(@CookieValue("refreshToken") String refreshToken) {
        if(refreshToken == null) {
            throw new CoordikittyException(ErrorType.INVALID_TOKEN);
        }
        TokenDto tokenDto = authService.reissueToken(refreshToken);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("newAccessToken", tokenDto.accessToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(cookieUtil.addRtkCookie("refreshToken", tokenDto.refreshToken()).toString()).body(responseBody);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDto loginRequestDto){
        LoginResponseDto loginResponseDto = authService.signIn(loginRequestDto);
        LoginInfoResponseDto loginInfoResponseDto = LoginInfoResponseDto.of(loginResponseDto);

        return ResponseEntity.ok().header("Set-Cookie",
                cookieUtil.addRtkCookie("refreshToken", loginResponseDto.refreshToken()).toString())
                        .body(loginInfoResponseDto);
    }

    @PostMapping("/login/google")
    public ResponseEntity<?> googleLogin(@RequestBody SocialLoginRequestDto socialLoginRequestDto){
        return ResponseEntity.ok(authService.socialSignIn(socialLoginRequestDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok().body(authService.logout(userDetails.getUsername()));
    }
}
