package Coordinate.coordikittyBE.domain.auth.signup.controller;

import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpResponseDto;
import Coordinate.coordikittyBE.domain.auth.signup.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.ok(signUpService.signUp(signUpRequestDto));
    }
}
