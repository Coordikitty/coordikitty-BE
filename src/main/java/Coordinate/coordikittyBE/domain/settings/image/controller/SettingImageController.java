package Coordinate.coordikittyBE.domain.settings.image.controller;

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
        return ResponseEntity.ok(settingImageService.getSettingImage(userDetails.getUsername()));
    }

    @PostMapping(value = "/image")
    public ResponseEntity<?> setSettingImage(
            @RequestPart("profileImg") MultipartFile profileImg,           // setting image request 사용 불가
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(settingImageService.changeSettingImage(userDetails.getUsername(), profileImg));
    }

    @DeleteMapping(value = "/image")
    public ResponseEntity<?> deleteSettingImage(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(settingImageService.deleteSettingImage(userDetails.getUsername()));
    }
}
