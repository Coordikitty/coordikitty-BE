package Coordinate.coordikittyBE.domain.settings.profile.controller;

import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileRequestDto;
import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileResponseDto;
import Coordinate.coordikittyBE.domain.settings.profile.service.SettingProfileService;
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
    public ResponseEntity<SettingProfileResponseDto> getSettingProfile(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token Authentication
        // User Entity : user id 반환
        // user id 에서 profile Data 반환
        String email = userDetails.getUsername();
        SettingProfileResponseDto settingProfileResponseDTO = settingProfileService.getSettingProfile(email);
        return ResponseEntity.ok().body(settingProfileResponseDTO);
    }

    @PostMapping(value = "/profile")
    public ResponseEntity<String> setSettingProfile(
            @RequestBody SettingProfileRequestDto nickname,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token Authentication
        // User Entity : user id 반환
        // user nickname 수정
        String email = userDetails.getUsername();
        if(settingProfileService.setSettingProfile(email, nickname))
            return ResponseEntity.ok().body("닉네임 변경 성공");
        else
            return ResponseEntity.badRequest().body("닉네임 변경 실패");
    }
}
