package Coordinate.coordikittyBE.domain.settings.profile.controller;

import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileRequestDTO;
import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileResponseDTO;
import Coordinate.coordikittyBE.domain.settings.profile.service.SettingProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingProfileController {

    private final SettingProfileService settingProfileService;

    @GetMapping(value = "/profile")
    public ResponseEntity<SettingProfileResponseDTO> getSettingProfile(
            @RequestHeader("Authorization") String token
    ) {
        // token Authentication
        // User Entity : user id 반환
        // user id 에서 profile Data 반환
        String email = "userId";

        SettingProfileResponseDTO settingProfileResponseDTO = settingProfileService.getSettingProfile(email);

        return ResponseEntity.ok().body(settingProfileResponseDTO);
    }

    @PostMapping(value = "/profile")
    public ResponseEntity<String> setSettingProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody SettingProfileRequestDTO nickname
    ) {
        // token Authentication
        // User Entity : user id 반환
        // user nickname 수정
        String email = "userId";

        if(settingProfileService.setSettingProfile(email, nickname))
            return ResponseEntity.ok().body("Success");
        else
            return ResponseEntity.badRequest().body("Fail");
    }
}
