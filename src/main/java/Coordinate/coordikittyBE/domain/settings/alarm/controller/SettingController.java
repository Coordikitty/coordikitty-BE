package Coordinate.coordikittyBE.domain.settings.alarm.controller;

import Coordinate.coordikittyBE.domain.settings.alarm.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
public class SettingController {

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

    @GetMapping(value = "/image")
    public ResponseEntity<SettingImageResponseDTO> getSettingImage(
            @RequestHeader("Authorization") String token
    ) {
        // token authorization
        // User Entity : user id 반환
        // User Entity : profileURL 찾기
        // profileURL 반환
        return ResponseEntity.ok().body(new SettingImageResponseDTO());
    }

    @PostMapping(value = "/image")
    public ResponseEntity<String> setSettingImage(
            @RequestHeader("Authorization") String token,
            @RequestBody SettingImageRequestDTO profileImg
    ) {
        // token authentication
        // User Entity : user id 반환
        // profileImg 업로드 후에 url 저장
        return ResponseEntity.ok().body("OK");
    }

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
