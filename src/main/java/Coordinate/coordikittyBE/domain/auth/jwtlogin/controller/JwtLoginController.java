package Coordinate.coordikittyBE.domain.auth.jwtlogin.controller;


import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.JwtTokenDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.service.JwtLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JwtLoginController {
    private final JwtLoginService jwtLoginService;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDto loginRequestDto){
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        JwtTokenDto jwtTokenDto = jwtLoginService.signIn(email, password);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + jwtTokenDto.getAccessToken()).build();
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
