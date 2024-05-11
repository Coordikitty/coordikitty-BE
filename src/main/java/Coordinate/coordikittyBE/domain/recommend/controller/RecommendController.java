package Coordinate.coordikittyBE.domain.recommend.controller;

import Coordinate.coordikittyBE.domain.recommend.dto.RecommendGetResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @GetMapping(value = "")
    public ResponseEntity<List<RecommendGetResponseDTO>> getStyle(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "situation", required = false) String situation,
            @RequestParam(value = "style", required = false) String style
    ) {
        // token authentication
        // User Entity : user id 반환
        // user id, [situation|style] 값 option 따라 ML에 전송 (필요하면 여기서 기온도 같이?)
        // 추천 옷 리스트 반환
        List<RecommendGetResponseDTO> recommendGetResponseDTOList = new ArrayList<>();
        return ResponseEntity.ok().body(recommendGetResponseDTOList);
    }
}
