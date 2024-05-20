package Coordinate.coordikittyBE.domain.settings.image.controller;

import Coordinate.coordikittyBE.domain.settings.image.dto.SettingImageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/setting")
public class SettingImageController {

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
            @RequestBody MultipartFile profileImg           // setting image request 사용 불가
    ) {
        // token authentication
        // User Entity : user id 반환
        // profileImg 업로드 후에 url 저장
        return ResponseEntity.ok().body("OK");
    }
}
