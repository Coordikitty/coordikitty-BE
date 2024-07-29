package Coordinate.coordikittyBE.domain.user.controller;

import Coordinate.coordikittyBE.domain.user.service.DupCheckService;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/signUp")
@RequiredArgsConstructor
public class DupCheckController {
    private final DupCheckService dupCheckService;

    @GetMapping("/dupCheck")
    public ResponseEntity<Boolean> dupCheck(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "nickname", required = false) String nickname
    ) {
            if(email != null && nickname == null) {
                return ResponseEntity.ok(dupCheckService.emailDupCheck(email));
            }
            else if(nickname != null && email == null) {
                return ResponseEntity.ok(dupCheckService.nicknameDupCheck(nickname));
            }
            throw new CoordikittyException(ErrorType.INVALID_PARAMS);
    }

}
