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
    public ResponseEntity<?> getSettingAlarm(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(settingService.getSettingAlarm(userDetails.getUsername()));
    }

    @PostMapping(value = "/alarm")
    public ResponseEntity<String> setSettingAlarm(
            @RequestBody SettingAlarmRequestDto type,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        settingService.changeSettingAlarm(userDetails.getUsername(), type);
        return ResponseEntity.ok().body("타입 변환 성공");
    }

}
