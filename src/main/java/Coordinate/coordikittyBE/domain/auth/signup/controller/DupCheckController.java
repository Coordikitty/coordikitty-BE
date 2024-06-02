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

    @PostMapping("/dupCheck")
    public ResponseEntity<String> DupCheck(@RequestParam(required = false) final String email, @RequestParam(required = false) final String nickname) {
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
