package Coordinate.coordikittyBE.domain.auth.signup.controller;

import Coordinate.coordikittyBE.domain.auth.signup.service.DupCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/signUp")
@RequiredArgsConstructor
public class DupCheckController {
    private final DupCheckService dupCheckService;

    @GetMapping("/dupCheck")
    public ResponseEntity<?> dupCheck(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "nickname", required = false) String nickname
    ) {
        try {
            if(email != null && nickname == null) {
                return ResponseEntity.ok(dupCheckService.emailDupCheck(email));
            }
            else if(nickname != null && email == null) {
                return ResponseEntity.ok(dupCheckService.nicknameDupCheck(nickname));
            }
            return ResponseEntity.badRequest().body("bad params");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
