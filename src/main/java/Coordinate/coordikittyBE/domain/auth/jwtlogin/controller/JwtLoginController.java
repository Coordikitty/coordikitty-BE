package Coordinate.coordikittyBE.domain.auth.jwtlogin.controller;


import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.JwtTokenDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class JwtLoginController {
    private final UserService userService;

    @PostMapping("")
    public JwtTokenDto signIn(@RequestBody LoginRequestDto loginRequestDto){
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        JwtTokenDto jwtTokenDto = userService.signIn(email, password);
        log.info("request username = {}, password = {}", email, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtTokenDto.getAccessToken(),jwtTokenDto.getRefreshToken());
        return jwtTokenDto;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
