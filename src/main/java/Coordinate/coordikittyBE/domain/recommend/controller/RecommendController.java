package Coordinate.coordikittyBE.domain.recommend.controller;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import Coordinate.coordikittyBE.domain.recommend.dto.CoordinatesDto;
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
    public ResponseEntity<?> getRecommend(
            @RequestParam(value = "situation", required = false) Situation situation,
            @RequestParam(value = "style", required = false) Style style,
            @RequestParam(value = "lat") double lat,
            @RequestParam(value = "lon") double lon,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token authentication
        // User Entity : user id 반환
        // user id, [situation|style] 값 option 따라 ML에 전송 (필요하면 여기서 기온도 같이?)
        // 추천 옷 리스트 반환

        try {
            // 잘못된 parameter
            if ((situation != null && style != null) || (style == null && situation == null))
                return ResponseEntity.badRequest().body("wrong parameter");

            String email = userDetails.getUsername();
            CoordinatesDto coordinatesDto = CoordinatesDto.fromCoordinates(lat, lon);

            List<RecommendGetResponseDto> recommendGetResponseDtos;

            if (situation != null)
                recommendGetResponseDtos = recommendService.getRecommend(email, situation, coordinatesDto);
            else
                recommendGetResponseDtos = recommendService.getRecommend(email, style, coordinatesDto);

            return ResponseEntity.ok(recommendGetResponseDtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
