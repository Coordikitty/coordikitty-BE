package Coordinate.coordikittyBE.domain.closet.controller;

import Coordinate.coordikittyBE.domain.closet.dto.ClosetCategorizationResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetPostRequestDto;
import Coordinate.coordikittyBE.domain.closet.service.ClosetService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/closet")
@RequiredArgsConstructor
public class ClosetController {

//    private final JwtTokenProvider jwtTokenProvider;
    private final ClosetService closetService;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllClothes(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<ClosetGetResponseDto> closetGetResponseDtos = closetService.getAllClothes(userDetails.getUsername());
        return ResponseEntity.ok(closetGetResponseDtos);
    }

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postCloth(
            @RequestPart("closetPostRequestDto") ClosetPostRequestDto closetPostRequestDto,
            @RequestPart("clothImg") MultipartFile clothImg,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        try {
            return ResponseEntity.ok(closetService.postCloth(userDetails.getUsername(), closetPostRequestDto, clothImg));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/categorization")
    public ResponseEntity<?> clothCategorization(
//            @RequestParam(value = "clothId") UUID clothId
//            @RequestBody MultipartFile file
            @RequestPart("clothImg") MultipartFile clothImg
    ) {
        // token authentication
        // DL 서버에 파일 전송, 분류 결과 반환
        // 분류 결과 클라이언트에 반환
        try {
            return ResponseEntity.ok().body(closetService.clothCategorization(clothImg));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "")
    public ResponseEntity<String> deleteCloth(
            @RequestParam(value = "clothId") UUID clothId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        closetService.deleteCloth(clothId, userDetails.getUsername());
        return ResponseEntity.ok().body("옷 삭제 성공");
    }
}
