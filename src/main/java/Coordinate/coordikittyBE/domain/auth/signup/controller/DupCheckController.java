package Coordinate.coordikittyBE.domain.auth.signup.controller;

import Coordinate.coordikittyBE.domain.auth.signup.service.DupCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/signup")
@RequiredArgsConstructor
public class DupCheckController {
    private final DupCheckService dupCheckService;

    @PostMapping("/dupcheck")
    public ResponseEntity<String> emailDupCheck(@RequestParam final String email) {
        try {
            return ResponseEntity.ok(dupCheckService.emailDupCheck(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/dupcheck")
    public ResponseEntity<String> nicknameDupCheck(@RequestParam final String nickname) {
        try {
            return ResponseEntity.ok(dupCheckService.nicknameDupCheck(nickname));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
