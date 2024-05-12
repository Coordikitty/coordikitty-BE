package Coordinate.coordikittyBE.domain.closet.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.AuthRepository;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClosetService {

    private final AuthRepository authRepository;
    private final ClothRepository clothRepository;

    public List<ClosetGetResponseDto> getAllClothes(String email) {
        // email 과 일치하는 cloth 반환
        Optional<UserEntity> userEntity = authRepository.findById(email);
        List<ClothEntity> temp = clothRepository.findAllByUserEntity(userEntity.orElse(null));

        List<ClosetGetResponseDto> closetGetResponseDtos = new ArrayList<>();
        for (ClothEntity clothEntity : temp) {
            ClosetGetResponseDto closetGetResponseDto = new ClosetGetResponseDto();
            closetGetResponseDto.setClothId(clothEntity.getClothId());

            closetGetResponseDto.setLarge(clothEntity.getLarge());
            closetGetResponseDto.setMedium(clothEntity.getMedium());
            closetGetResponseDto.setSmall(clothEntity.getSmall());

            closetGetResponseDto.setFit(clothEntity.getFit());
            closetGetResponseDto.setGender(clothEntity.getGender());
            closetGetResponseDto.setSeason(clothEntity.getSeason());
            closetGetResponseDto.setStyle(clothEntity.getStyle());
            closetGetResponseDto.setThickness(clothEntity.getThickness());

            closetGetResponseDto.setClothURL(clothEntity.getPictureURL());

            closetGetResponseDtos.add(closetGetResponseDto);
        }

        return closetGetResponseDtos;
    }
}
