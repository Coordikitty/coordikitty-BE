package Coordinate.coordikittyBE.domain.settings.profile.controller;

import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileRequestDTO;
import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
public class SettingProfileController {

    @GetMapping(value = "/profile")
    public ResponseEntity<SettingProfileResponseDTO> getSettingProfile(
            @RequestHeader("Authorization") String token
    ) {
        // token Authentication
        // User Entity : user id 반환
        // user id 에서 profile Data 반환
        return ResponseEntity.ok().body(new SettingProfileResponseDTO());
    }

    @PostMapping(value = "/profile")
    public ResponseEntity<String> setSettingProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody SettingProfileRequestDTO nickname
    ) {
        // token Authentication
        // User Entity : user id 반환
        // user nickname 수정
        return ResponseEntity.ok().body("OK");
    }
}
