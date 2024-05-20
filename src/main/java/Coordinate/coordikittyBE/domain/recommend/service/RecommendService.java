package Coordinate.coordikittyBE.domain.recommend.service;

import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.recommend.dto.RecommendGetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecommendService {

    private final ClothRepository clothRepository;

    public Double getTemperature() {
        // 날씨 api 에서 날씨 정보 받아오기
        return 36.5;
    }

    public List<RecommendGetResponseDTO> getSituation(String email, String situation) {
        // Situation 추천 ML 에 user id, situation 전송
        // 코디 = 옷 List 반환
        List<RecommendGetResponseDTO> recommendGetResponseDTOS = new ArrayList<>();
        return recommendGetResponseDTOS;
    }

    public List<RecommendGetResponseDTO> getStyle(String email, String style) {
        // Style 추천 ML 에 user id, style 전송
        // 코디 = 옷 List 반환
        List<RecommendGetResponseDTO> recommendGetResponseDTOS = new ArrayList<>();
        return recommendGetResponseDTOS;
    }
}
