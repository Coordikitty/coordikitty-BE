package Coordinate.coordikittyBE.domain.recommend.controller;

import Coordinate.coordikittyBE.domain.recommend.dto.RecommendGetResponseDto;
import Coordinate.coordikittyBE.domain.recommend.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping(value = "")
    public ResponseEntity<List<RecommendGetResponseDto>> getRecommend(
            @RequestParam(value = "situation", required = false) String situation,
            @RequestParam(value = "style", required = false) String style,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token authentication
        // User Entity : user id 반환
        // user id, [situation|style] 값 option 따라 ML에 전송 (필요하면 여기서 기온도 같이?)
        // 추천 옷 리스트 반환

        String email = userDetails.getUsername();

        if (situation != null && style != null)
            return ResponseEntity.badRequest().body(null);

        if (style == null && situation == null)
            return ResponseEntity.badRequest().body(null);

        List<RecommendGetResponseDto> recommendGetResponseDtos;
        if (style == null)
            recommendGetResponseDtos = recommendService.getSituation(email, situation);
        else
            recommendGetResponseDtos = recommendService.getStyle(email, style);

        return ResponseEntity.ok().body(recommendGetResponseDtos);
    }
}
