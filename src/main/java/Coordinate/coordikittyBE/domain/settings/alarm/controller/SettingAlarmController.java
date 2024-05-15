package Coordinate.coordikittyBE.domain.settings.alarm.controller;

import Coordinate.coordikittyBE.domain.settings.alarm.dto.*;
import Coordinate.coordikittyBE.domain.settings.alarm.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingAlarmController {

    private final SettingService settingService;

    @GetMapping(value = "/alarm")
    public ResponseEntity<SettingAlarmResponseDTO> getSettingAlarm(
            @RequestHeader("Authorization") String token
    ) {
        // token authorization
        // User Entity : user id 반환
        // user id 에 해당하는 alarm type 반환

        String userId = "user id";
        SettingAlarmResponseDTO settingAlarmResponseDTO = settingService.getSettingAlarm(userId);

        return ResponseEntity.ok().body(settingAlarmResponseDTO);
    }

    @PostMapping(value = "/alarm")
    public ResponseEntity<String> setSettingAlarm(
            @RequestHeader("Authorization") String token,
            @RequestBody SettingAlarmRequestDTO type
    ) {
        // token authorization
        // User Entity : user id 반환
        // alarm type 수정

        String userId = "user id";
        if (settingService.setSettingAlarm(userId, type))
            return ResponseEntity.ok().body("OK");
        else
            return ResponseEntity.badRequest().body("타입 변환 실패");
    }

}
