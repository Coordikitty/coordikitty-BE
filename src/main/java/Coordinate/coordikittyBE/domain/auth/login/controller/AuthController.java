package Coordinate.coordikittyBE.domain.auth.login.controller;

import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginInfoResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.request.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.request.SocialLoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.service.UserService;
import Coordinate.coordikittyBE.global.util.cookie.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final CookieUtil cookieUtil;

    @PostMapping("/token")
    public ResponseEntity<?> createNewAccessToken(@CookieValue("refreshToken") String refreshToken) {
        TokenDto tokenDto = userService.reissueToken(refreshToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDto loginRequestDto){
        LoginResponseDto loginResponseDto = userService.signIn(loginRequestDto);
        LoginInfoResponseDto loginInfoResponseDto = LoginInfoResponseDto.of(loginResponseDto);

        return ResponseEntity.ok().header("Set-Cookie",
                cookieUtil.addRtkCookie("refreshToken", loginResponseDto.refreshToken()).toString())
                        .body(loginInfoResponseDto);
    }

    @PostMapping("/login/google")
    public ResponseEntity<?> googleLogin(@RequestBody SocialLoginRequestDto socialLoginRequestDto){
        return ResponseEntity.ok(userService.socialSignIn(socialLoginRequestDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok().body(userService.logout(userDetails.getUsername()));
    }
}
