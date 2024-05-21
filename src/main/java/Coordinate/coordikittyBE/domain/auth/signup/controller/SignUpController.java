package Coordinate.coordikittyBE.domain.auth.signup.controller;

import Coordinate.coordikittyBE.domain.auth.signup.dto.SignupRequestDTO;
import Coordinate.coordikittyBE.domain.auth.signup.service.SignUpService;
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
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody final SignupRequestDTO signUpRequestDto) {
        try {
            return signUpService.signUp(signUpRequestDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}