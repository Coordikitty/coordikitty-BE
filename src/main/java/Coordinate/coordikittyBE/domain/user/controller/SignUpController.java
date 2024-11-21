package Coordinate.coordikittyBE.domain.user.controller;

import Coordinate.coordikittyBE.domain.user.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.user.dto.SignUpResponseDto;
import Coordinate.coordikittyBE.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SignUpController {
    private final SignUpService signUpService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.ok(signUpService.signUp(signUpRequestDto));
    }
}
