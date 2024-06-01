package Coordinate.coordikittyBE.domain.closet.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetCategorizationResponseDTO;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetPostRequestDto;
import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClosetService {

    private final UserRepository userRepository;
    private final ClothRepository clothRepository;
    private final ClothImageService clothImageService;

    public List<ClosetGetResponseDto> getAllClothes(String email) {
        // email 과 일치하는 cloth 반환
        UserEntity userEntity = userRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("옷 조회 실패"));

        List<ClothEntity> temp = clothRepository.findAllByUserEntity(userEntity);
        List<ClosetGetResponseDto> closetGetResponseDtos = new ArrayList<>();
        for (ClothEntity clothEntity : temp) {
            ClosetGetResponseDto closetGetResponseDto = new ClosetGetResponseDto();

            ClosetGetResponseDto.builder()
                    .clothId(clothEntity.getClothId())
                    .large(clothEntity.getLarge())
                    .medium(clothEntity.getMedium())
                    .small(clothEntity.getSmall())
                    .fit(clothEntity.getFit())
                    .gender(clothEntity.getGender())
                    .season(clothEntity.getSeason())
                    .style(clothEntity.getStyle())
                    .thickness(clothEntity.getThickness())
                    .clothURL(clothEntity.getPictureURL())
                    .build();

            closetGetResponseDtos.add(closetGetResponseDto);
        }

        return closetGetResponseDtos;
    }

    @Transactional
    public void postCloth(String email, ClosetPostRequestDto closetPostRequestDto, MultipartFile clothImg) {
        try {
            UserEntity userEntity = userRepository.findById(email)
                    .orElseThrow(() -> new RuntimeException("옷 추가 실패: 없는 유저 email"));

            String url = clothImageService.storeImgToFirebase(clothImg);
            if (url == null) throw new RuntimeException("옷 추가 실패: 이미지 저장 실패");

            ClothEntity clothEntity = new ClothEntity();

            ClothEntity.builder()
                    .clothId(UUID.randomUUID())
                    .userEntity(userEntity)
                    .large(closetPostRequestDto.getLarge())
                    .medium(closetPostRequestDto.getMedium())
                    .small(closetPostRequestDto.getSmall())
                    .fit(closetPostRequestDto.getFit())
                    .gender(closetPostRequestDto.getGender())
                    .season(closetPostRequestDto.getSeason())
                    .style(closetPostRequestDto.getStyle())
                    .thickness(closetPostRequestDto.getThickness())
                    .pictureURL(url)
                    .build();

            clothRepository.save(clothEntity);
        } catch (Exception e) {
            throw new RuntimeException("옷 추가 실패" + e.getMessage(), e);
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
