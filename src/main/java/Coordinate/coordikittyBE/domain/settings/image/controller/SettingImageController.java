package Coordinate.coordikittyBE.domain.settings.image.controller;

import Coordinate.coordikittyBE.domain.settings.image.dto.SettingImageResponseDTO;
import Coordinate.coordikittyBE.domain.settings.image.service.SettingImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingImageController {

    private final SettingImageService settingImageService;

    @GetMapping(value = "/image")
    public ResponseEntity<SettingImageResponseDTO> getSettingImage(
            @RequestHeader("Authorization") String token,
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        // token authorization
        // User Entity : user id 반환
        // User Entity : profileURL 찾기
        // profileURL 반환
        String email = userDetails.getUsername();
        SettingImageResponseDTO settingImageResponseDTO = settingImageService.getSettingImage(email);
        return ResponseEntity.ok().body(settingImageResponseDTO);
    }

    @PostMapping(value = "/image")
    public ResponseEntity<String> setSettingImage(
            @RequestHeader("Authorization") String token,
            @RequestPart("profileImg") MultipartFile profileImg,           // setting image request 사용 불가
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token authentication
        // User Entity : user id 반환
        // profileImg 업로드 후에 url 저장
        String email = userDetails.getUsername();
        if (settingImageService.setSettingImage(email, profileImg))
            return ResponseEntity.ok().body("프로필 이미지 변경 성공");
        else
            return ResponseEntity.badRequest().body("프로필 이미지 변경 실패");
    }
}
