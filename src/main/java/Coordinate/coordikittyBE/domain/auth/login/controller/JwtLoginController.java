package Coordinate.coordikittyBE.domain.auth.login.controller;

import Coordinate.coordikittyBE.domain.auth.login.dto.JwtTokenRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.service.TokenService;
import Coordinate.coordikittyBE.domain.auth.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class JwtLoginController {
    private final TokenService tokenService;
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

    @GetMapping("/login/google")
    public ResponseEntity<?> googleLogin(){
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/oauth2/authorization/google"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
    @GetMapping("/test")
    public String test(){
        return "hi";
    }
}
