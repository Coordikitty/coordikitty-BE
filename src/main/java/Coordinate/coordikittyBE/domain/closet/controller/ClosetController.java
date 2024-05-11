package Coordinate.coordikittyBE.domain.closet.controller;

import Coordinate.coordikittyBE.domain.closet.dto.ClosetCategorizationResponseDTO;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetDeleteRequestDTO;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetPostRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/closet")
public class ClosetController {

    @GetMapping(value = "")
    public ResponseEntity<List<ClosetGetResponseDto>> getAllClothes(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "email") String email
    ) {
        // token authentication
        // User Entity : query string email 에 해당하는 user id 반환
        // Cloth Entity : user id가 일치하는 tuple 반환
        // 찾은 tuple 리스트로 만들어서 반환
        List<ClosetGetResponseDto> closetGetResponseDtos = new ArrayList<>();
        return ResponseEntity.ok().body(closetGetResponseDtos);
    }

    @PostMapping(value = "")
    public ResponseEntity<String> postCloth(
            @RequestHeader("Authorization") String token,
            @RequestBody ClosetPostRequestDTO closetPostRequestDTO
    ) {
        // token authentication
        // User Entity : user id 반환
        // Cloth Entity 에 옷 추가
        return ResponseEntity.ok().body("옷 추가 성공");
    }

    @PostMapping(value = "/categorization")
    public ResponseEntity<ClosetCategorizationResponseDTO> clothCategorization(
            @RequestHeader("Authorization") String token,
            @RequestBody MultipartFile file
    ) {
        // token authentication
        // DL 서버에 파일 전송, 분류 결과 반환
        // 분류 결과 클라이언트에 반환
        return ResponseEntity.ok().body(new ClosetCategorizationResponseDTO());
    }

    @DeleteMapping(value = "")
    public ResponseEntity<String> deleteCloth(
            @RequestHeader("Authorization") String token,
            @RequestBody ClosetDeleteRequestDTO closetDeleteRequestDTO
    ) {
        // token authentication
        // User Entity : user id 반환
        // Cloth Entity : cloth id 튜플 삭제 (Cascade -> attach)
        return ResponseEntity.ok().body("옷 삭제 성공");
    }
}
