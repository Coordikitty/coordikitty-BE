package Coordinate.coordikittyBE.domain.settings.alarm.controller;

import Coordinate.coordikittyBE.domain.settings.alarm.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
public class SettingAlarmController {

    @GetMapping(value = "/alarm")
    public ResponseEntity<SettingAlarmResponseDTO> getSettingAlarm(
            @RequestHeader("Authorization") String token
    ) {
        // token authorization
        // User Entity : user id 반환
        // user id 에 해당하는 alarm type 반환
        return ResponseEntity.ok().body(new SettingAlarmResponseDTO());
    }

    @PostMapping(value = "/alarm")
    public ResponseEntity<String> setSettingAlarm(
            @RequestHeader("Authorization") String token,
            @RequestBody SettingAlarmRequestDTO type
    ) {
        // token authorization
        // User Entity : user id 반환
        // alarm type 수정
        return ResponseEntity.ok().body("OK");
    }

}
