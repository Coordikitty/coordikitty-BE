package Coordinate.coordikittyBE.domain.settings.profile.controller;

import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileRequestDto;
import Coordinate.coordikittyBE.domain.settings.profile.service.SettingProfileService;
import Coordinate.coordikittyBE.global.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingProfileController {

    private final SettingProfileService settingProfileService;

    @GetMapping(value = "/profile")
    public ResponseEntity<?> getSettingProfile(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token Authentication
        // User Entity : user id 반환
        // user id 에서 profile Data 반환
        return ResponseEntity.ok(settingProfileService.getSettingProfile(userDetails.getUsername()));
    }

    @PostMapping(value = "/profile")
    public ResponseEntity<?> setSettingProfile(
            @RequestBody SettingProfileRequestDto nickname,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token Authentication
        // User Entity : user id 반환
        // user nickname 수정

        settingProfileService.setSettingProfile(userDetails.getUsername(), nickname);
        return ResponseEntity.ok().body(SuccessResponse.from("닉네임 변경 성공"));
    }
}
