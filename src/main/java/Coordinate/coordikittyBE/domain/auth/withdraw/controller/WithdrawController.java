package Coordinate.coordikittyBE.domain.auth.withdraw.controller;


import Coordinate.coordikittyBE.domain.auth.withdraw.service.WithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class WithdrawController {
    private final WithdrawService withdrawService;

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody String email){
        try {
            return ResponseEntity.ok(withdrawService.withdraw(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("삭제 오류");
        }
    }
}
