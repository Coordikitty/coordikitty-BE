package Coordinate.coordikittyBE.domain.user.controller;

import Coordinate.coordikittyBE.domain.user.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.user.dto.SignUpResponseDto;
import Coordinate.coordikittyBE.domain.user.service.SignUpService;
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
