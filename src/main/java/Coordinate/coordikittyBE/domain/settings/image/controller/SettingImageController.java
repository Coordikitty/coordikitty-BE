package Coordinate.coordikittyBE.domain.settings.image.controller;

import Coordinate.coordikittyBE.domain.settings.image.dto.SettingImageResponseDto;
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
    public ResponseEntity<?> getSettingImage(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token authorization
        // User Entity : user id 반환
        // User Entity : profileURL 찾기
        // profileURL 반환
        try {
            SettingImageResponseDto settingImageResponseDTO = settingImageService.getSettingImage(userDetails.getUsername());
            return ResponseEntity.ok().body(settingImageResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/image")
    public ResponseEntity<String> setSettingImage(
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
