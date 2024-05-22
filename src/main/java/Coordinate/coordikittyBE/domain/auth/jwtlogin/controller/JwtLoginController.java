package Coordinate.coordikittyBE.domain.auth.jwtlogin.controller;


import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.JwtTokenDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody final SignUpRequestDto signUpRequestDto) {
        try {
            return userService.signUp(signUpRequestDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public JwtTokenDto signIn(@RequestBody LoginRequestDto loginRequestDto){
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        return userService.signIn(email, password);
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
