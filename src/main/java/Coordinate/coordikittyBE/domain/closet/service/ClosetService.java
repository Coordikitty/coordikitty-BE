package Coordinate.coordikittyBE.domain.closet.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetCategorizationResponseDTO;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetPostRequestDTO;
import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClosetService {

    private final UserRepository userRepository;
    private final ClothRepository clothRepository;

    public List<ClosetGetResponseDto> getAllClothes(String email) {
        // email 과 일치하는 cloth 반환
        Optional<UserEntity> userEntityOptional = userRepository.findById(email);

        if (userEntityOptional.isEmpty()) return new ArrayList<>();
        UserEntity userEntity = userEntityOptional.get();

        List<ClothEntity> temp = clothRepository.findAllByUserEntity(userEntity);
        List<ClosetGetResponseDto> closetGetResponseDtos = new ArrayList<>();
        for (ClothEntity clothEntity : temp) {
            ClosetGetResponseDto closetGetResponseDto = getClosetGetResponseDto(clothEntity);
            closetGetResponseDtos.add(closetGetResponseDto);
        }

        return closetGetResponseDtos;
    }

    private static ClosetGetResponseDto getClosetGetResponseDto(ClothEntity clothEntity) {
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
        return closetGetResponseDto;
    }

    @Transactional
    public boolean postCloth(String email, ClosetPostRequestDTO closetPostRequestDTO, MultipartFile clothImg) {
        try {
            // for test
            System.out.println("1");

            Optional<UserEntity> userEntityOptional = userRepository.findById(email);

            if (userEntityOptional.isEmpty()) return false;

            UserEntity userEntity = userEntityOptional.get();
            ClothEntity clothEntity = new ClothEntity();

            clothEntity.setUserEntity(userEntity);

            clothEntity.setLarge(closetPostRequestDTO.getLarge());
            clothEntity.setMedium(closetPostRequestDTO.getMedium());
            clothEntity.setSmall(closetPostRequestDTO.getSmall());

            clothEntity.setFit(closetPostRequestDTO.getFit());
            clothEntity.setGender(closetPostRequestDTO.getGender());
            clothEntity.setSeason(closetPostRequestDTO.getSeason());
            clothEntity.setStyle(closetPostRequestDTO.getStyle());
            clothEntity.setThickness(closetPostRequestDTO.getThickness());

//            MultipartFile file = closetPostRequestDTO.getClothImg();
            // file firebase 에 저장 -> url 반환
            clothEntity.setPictureURL("url");

            // for test
            System.out.println("print 2");

            clothRepository.save(clothEntity);

            // for test
            System.out.println("print 3");

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ClosetCategorizationResponseDTO clothCategorization(MultipartFile file) {
        // file DL 서버에 전송
        // 반환 값 DTO 에 저장
        return new ClosetCategorizationResponseDTO();
    }

    public boolean deleteCloth(UUID clothId) {
        if (!clothRepository.existsById(clothId)) return false;

        clothRepository.deleteById(clothId);
        return true;
    }
}
