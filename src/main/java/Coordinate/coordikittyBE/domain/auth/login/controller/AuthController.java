package Coordinate.coordikittyBE.domain.auth.login.controller;

import Coordinate.coordikittyBE.domain.auth.login.dto.JwtTokenRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LogoutDto;
import Coordinate.coordikittyBE.domain.auth.login.service.TokenService;
import Coordinate.coordikittyBE.domain.auth.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDto loginRequestDto){
        try {
            return ResponseEntity.ok().body(userService.signIn(loginRequestDto));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/login/google")
    public ResponseEntity<?> googleLogin(){
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/oauth2/authorization/google"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestBody JwtTokenRequestDto refreshTokenDto,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        try {
            userService.logout(LogoutDto.of(userDetails.getUsername(), refreshTokenDto.refreshToken()));
            return ResponseEntity.ok().body("logout success");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
