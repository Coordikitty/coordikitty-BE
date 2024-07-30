package Coordinate.coordikittyBE.domain.auth.withdraw.controller;


import Coordinate.coordikittyBE.domain.auth.withdraw.dto.WithdrawRequestDto;
import Coordinate.coordikittyBE.domain.auth.withdraw.service.WithdrawService;
import Coordinate.coordikittyBE.global.common.response.SuccessResponse;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> withdraw(@Valid @RequestBody WithdrawRequestDto withdrawRequestDto){
        withdrawService.withdraw(withdrawRequestDto.email());
        return ResponseEntity.ok(SuccessResponse.from("삭제 성공"));
    }
}
