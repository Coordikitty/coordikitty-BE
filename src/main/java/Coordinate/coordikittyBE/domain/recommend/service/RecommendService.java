package Coordinate.coordikittyBE.domain.recommend.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.recommend.dto.RecommendGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecommendService {

    private final UserRepository userRepository;
    private final ClothRepository clothRepository;

    public Double getTemperature() {
        // 날씨 api 에서 날씨 정보 받아오기
        return 36.5;
    }

    public List<RecommendGetResponseDto> getSituation(String email, String situation) {
        // Situation 추천 ML 에 user id, situation 전송
        // 코디 = 옷 List 반환

        Optional<User> optionalUserEntity = userRepository.findById(email);
        List<RecommendGetResponseDto> recommendGetResponseDtos = new ArrayList<>();

        if (optionalUserEntity.isEmpty()) return recommendGetResponseDtos;
        User user = optionalUserEntity.get();

        List<Cloth> clothEntities = clothRepository.findAllByUserEmail(user.getEmail());

        // situation 추천 ML 에 사용자가 가진 옷 리스트 전송
        // 추천 옷 리스트 반환 -> clothes

        for (Cloth cloth : clothEntities) {
            RecommendGetResponseDto recommendGetResponseDTO = getRecommendGetResponseDTO(cloth);

            recommendGetResponseDtos.add(recommendGetResponseDTO);
        }

        return recommendGetResponseDtos;
    }

    public List<RecommendGetResponseDto> getStyle(String email, String style) {
        // Style 추천 ML 에 user id, style 전송
        // 코디 = 옷 List 반환
        Optional<User> optionalUserEntity = userRepository.findById(email);
        List<RecommendGetResponseDto> recommendGetResponseDtos = new ArrayList<>();

        if (optionalUserEntity.isEmpty()) return recommendGetResponseDtos;
        User user = optionalUserEntity.get();

        List<Cloth> clothEntities = clothRepository.findAllByUserEmail(user.getEmail());

        // situation 추천 ML 에 사용자가 가진 옷 리스트 전송
        // 추천 옷 리스트 반환 -> clothes

        for (Cloth cloth : clothEntities) {
            RecommendGetResponseDto recommendGetResponseDTO = getRecommendGetResponseDTO(cloth);

            recommendGetResponseDtos.add(recommendGetResponseDTO);
        }

        return recommendGetResponseDtos;
    }

    private static RecommendGetResponseDto getRecommendGetResponseDTO(Cloth cloth) {
        RecommendGetResponseDto recommendGetResponseDTO  = new RecommendGetResponseDto();

        recommendGetResponseDTO.setLarge(cloth.getLarge());
        recommendGetResponseDTO.setMedium(cloth.getMedium());
        recommendGetResponseDTO.setSmall(cloth.getSmall());

        recommendGetResponseDTO.setFit(cloth.getFit());
        recommendGetResponseDTO.setGender(cloth.getGender());
        recommendGetResponseDTO.setSeason(cloth.getSeason());
        recommendGetResponseDTO.setStyle(cloth.getStyle());
        recommendGetResponseDTO.setThickness(cloth.getThickness());

        recommendGetResponseDTO.setClothURL(cloth.getPictureURL());
        return recommendGetResponseDTO;
    }
}
