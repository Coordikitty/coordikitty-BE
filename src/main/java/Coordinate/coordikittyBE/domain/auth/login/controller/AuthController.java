package Coordinate.coordikittyBE.domain.auth.login.controller;

import Coordinate.coordikittyBE.domain.auth.login.dto.request.JwtTokenRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.request.SocialLoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.service.TokenService;
import Coordinate.coordikittyBE.domain.auth.login.service.UserService;
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
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/token")
    public ResponseEntity<LoginResponseDto> createNewAccessToken(@RequestBody JwtTokenRequestDto tokenRequestDto) {
        LoginResponseDto newAccessToken = tokenService.createNewAccessToken(tokenRequestDto.refreshToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccessToken);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> signIn(@RequestBody LoginRequestDto loginRequestDto){
            return ResponseEntity.ok().body(userService.signIn(loginRequestDto));
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
