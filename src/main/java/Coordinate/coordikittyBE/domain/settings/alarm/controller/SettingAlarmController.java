package Coordinate.coordikittyBE.domain.settings.alarm.controller;

import Coordinate.coordikittyBE.domain.settings.alarm.dto.*;
import Coordinate.coordikittyBE.domain.settings.alarm.service.SettingAlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingAlarmController {

    private final SettingAlarmService settingService;

    @GetMapping(value = "/alarm")
    public ResponseEntity<SettingAlarmResponseDto> getSettingAlarm(
            @RequestHeader("Authorization") String token,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token authorization
        // User Entity : user id 반환
        // user id 에 해당하는 alarm type 반환

        String email = userDetails.getUsername();
        SettingAlarmResponseDto settingAlarmResponseDTO = settingService.getSettingAlarm(email);

        return ResponseEntity.ok().body(settingAlarmResponseDTO);
    }

    @PostMapping(value = "/alarm")
    public ResponseEntity<String> setSettingAlarm(
            @RequestHeader("Authorization") String token,
            @RequestBody SettingAlarmRequestDto type,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token authorization
        // User Entity : user id 반환
        // alarm type 수정

        String userId = userDetails.getUsername();
        if (settingService.setSettingAlarm(userId, type))
            return ResponseEntity.ok().body("타입 변환 성공");
        else
            return ResponseEntity.badRequest().body("타입 변환 실패");
    }

}
