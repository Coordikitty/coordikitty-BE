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
        // token authorization
        // User Entity : user id 반환
        // user id 에 해당하는 alarm type 반환

        return ResponseEntity.ok(settingService.getSettingAlarm(userDetails.getUsername()));

//        try {
//            SettingAlarmResponseDto settingAlarmResponseDTO = settingService.getSettingAlarm(userDetails.getUsername());
//            return ResponseEntity.ok().body(settingAlarmResponseDTO);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
    }

    @PostMapping(value = "/alarm")
    public ResponseEntity<String> setSettingAlarm(
            @RequestBody SettingAlarmRequestDto type,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token authorization
        // User Entity : user id 반환
        // alarm type 수정

        settingService.changeSettingAlarm(userDetails.getUsername(), type);
        return ResponseEntity.ok().body("타입 변환 성공");

//        try{
//            settingService.setSettingAlarm(userDetails.getUsername(), type);
//            return ResponseEntity.ok().body("타입 변환 성공");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
    }

}
