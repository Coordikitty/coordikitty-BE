package Coordinate.coordikittyBE.domain.recommend.controller;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import Coordinate.coordikittyBE.domain.recommend.dto.CoordinatesDto;
import Coordinate.coordikittyBE.domain.recommend.dto.RecommendGetResponseDto;
import Coordinate.coordikittyBE.domain.recommend.enums.Type;
import Coordinate.coordikittyBE.domain.recommend.service.RecommendService;
import Coordinate.coordikittyBE.domain.recommend.util.EnumUtil;
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
//            @RequestParam(value = "situation", required = false) Situation situation,
//            @RequestParam(value = "style", required = false) Style style,
            @RequestParam(value = "type") Type type,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "lat") double lat,
            @RequestParam(value = "lon") double lon,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // token authentication
        // User Entity : user id 반환
        // user id, [situation|style] 값 option 따라 ML에 전송 (필요하면 여기서 기온도 같이?)
        // 추천 옷 리스트 반환

        switch (type) {
//            case SITUATION -> {
//                if (!EnumUtil.isInEnum(value, Situation.class))
//                    return ResponseEntity.badRequest().body("wrong situation value");
//            }
            case STYLE -> {
                if (!EnumUtil.isInEnum(value, Style.class))
                    return ResponseEntity.badRequest().body("wrong style value");
            }
        }

        CoordinatesDto coordinatesDto = CoordinatesDto.toDto(lat, lon);
        return ResponseEntity.ok(recommendService.getRecommend(userDetails.getUsername(), type, value, coordinatesDto));
    }
}
