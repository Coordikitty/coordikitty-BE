package Coordinate.coordikittyBE.domain.closet.controller;

import Coordinate.coordikittyBE.domain.auth.jwtlogin.middleware.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetCategorizationResponseDTO;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetPostRequestDTO;
import Coordinate.coordikittyBE.domain.closet.service.ClosetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<ClosetGetResponseDto>> getAllClothes(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "email") String email
    ) {
        // token authentication
        // Cloth Entity : query string email 과 user id가 일치하는 tuple 반환
        // 찾은 tuple 리스트로 만들어서 반환

        List<ClosetGetResponseDto> closetGetResponseDtos = closetService.getAllClothes(email);
        return ResponseEntity.ok().body(closetGetResponseDtos);
    }

    @PostMapping(value = "")
    public ResponseEntity<String> postCloth(
            @RequestHeader("Authorization") String token,
//            @RequestBody ClosetPostRequestDTO closetPostRequestDTO,
//            @RequestBody MultipartFile clothImg,
            @RequestPart("closetPostRequestDTO") ClosetPostRequestDTO closetPostRequestDTO,
            @RequestPart("clothImg") MultipartFile clothImg,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token authentication
        // User Entity : user id 반환
        // Cloth Entity 에 옷 정보 추가, Firebase 에 옷 사진 업로드

        String email = userDetails.getUsername();
        System.out.println("email: " + email);
        if (closetService.postCloth(email, closetPostRequestDTO, clothImg))
            return ResponseEntity.ok().body("옷 추가 성공");
        else
            return ResponseEntity.ok().body("옷 추가 실패");
    }

    @PostMapping(value = "/categorization")
    public ResponseEntity<ClosetCategorizationResponseDTO> clothCategorization(
            @RequestHeader("Authorization") String token,
            @RequestBody MultipartFile file
    ) {
        // token authentication
        // DL 서버에 파일 전송, 분류 결과 반환
        // 분류 결과 클라이언트에 반환
        ClosetCategorizationResponseDTO closetCategorizationResponseDTO = closetService.clothCategorization(file);
        return ResponseEntity.ok().body(closetCategorizationResponseDTO);
    }

    @DeleteMapping(value = "")
    public ResponseEntity<String> deleteCloth(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "clothId") UUID clothId
    ) {
        // token authentication
        // User Entity : user id 반환
        // Cloth Entity : cloth id 튜플 삭제 (Cascade -> attach)

        if (closetService.deleteCloth(clothId))
            return ResponseEntity.ok().body("옷 삭제 성공");
        else
            return ResponseEntity.ok().body("옷 삭제 실패");
    }
}
