package Coordinate.coordikittyBE.domain.auth.jwtlogin.controller;

import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.JwtTokenRequestDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.middleware.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.service.TokenService;
import Coordinate.coordikittyBE.domain.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class JwtLoginController {
    private final TokenService tokenService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    @PostMapping("/token")
    public ResponseEntity<TokenDto> createNewAccessToken(@RequestBody JwtTokenRequestDto tokenRequestDto) {
        TokenDto newAccessToken = tokenService.createNewAccessToken(tokenRequestDto.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccessToken);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> signIn(@RequestBody LoginRequestDto loginRequestDto){
        TokenDto tokenDto = userService.signIn(loginRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenDto);
    }
}
