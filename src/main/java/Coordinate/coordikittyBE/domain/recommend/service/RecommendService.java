package Coordinate.coordikittyBE.domain.recommend.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
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

        Optional<UserEntity> optionalUserEntity = userRepository.findById(email);
        List<RecommendGetResponseDto> recommendGetResponseDtos = new ArrayList<>();

        if (optionalUserEntity.isEmpty()) return recommendGetResponseDtos;
        UserEntity userEntity = optionalUserEntity.get();

        List<ClothEntity> clothEntities = clothRepository.findAllByUserEntity(userEntity);

        // situation 추천 ML 에 사용자가 가진 옷 리스트 전송
        // 추천 옷 리스트 반환 -> clothes

        for (ClothEntity clothEntity : clothEntities) {
            RecommendGetResponseDto recommendGetResponseDTO = getRecommendGetResponseDTO(clothEntity);

            recommendGetResponseDtos.add(recommendGetResponseDTO);
        }

        return recommendGetResponseDtos;
    }

    public List<RecommendGetResponseDto> getStyle(String email, String style) {
        // Style 추천 ML 에 user id, style 전송
        // 코디 = 옷 List 반환
        Optional<UserEntity> optionalUserEntity = userRepository.findById(email);
        List<RecommendGetResponseDto> recommendGetResponseDtos = new ArrayList<>();

        if (optionalUserEntity.isEmpty()) return recommendGetResponseDtos;
        UserEntity userEntity = optionalUserEntity.get();

        List<ClothEntity> clothEntities = clothRepository.findAllByUserEntity(userEntity);

        // situation 추천 ML 에 사용자가 가진 옷 리스트 전송
        // 추천 옷 리스트 반환 -> clothes

        for (ClothEntity clothEntity : clothEntities) {
            RecommendGetResponseDto recommendGetResponseDTO = getRecommendGetResponseDTO(clothEntity);

            recommendGetResponseDtos.add(recommendGetResponseDTO);
        }

        return recommendGetResponseDtos;
    }

    private static RecommendGetResponseDto getRecommendGetResponseDTO(ClothEntity clothEntity) {
        RecommendGetResponseDto recommendGetResponseDTO  = new RecommendGetResponseDto();

        recommendGetResponseDTO.setLarge(clothEntity.getLarge());
        recommendGetResponseDTO.setMedium(clothEntity.getMedium());
        recommendGetResponseDTO.setSmall(clothEntity.getSmall());

        recommendGetResponseDTO.setFit(clothEntity.getFit());
        recommendGetResponseDTO.setGender(clothEntity.getGender());
        recommendGetResponseDTO.setSeason(clothEntity.getSeason());
        recommendGetResponseDTO.setStyle(clothEntity.getStyle());
        recommendGetResponseDTO.setThickness(clothEntity.getThickness());

        recommendGetResponseDTO.setClothURL(clothEntity.getPictureURL());
        return recommendGetResponseDTO;
    }
}
