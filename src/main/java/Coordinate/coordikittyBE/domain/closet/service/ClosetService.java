package Coordinate.coordikittyBE.domain.closet.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetCategorizationResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetPostRequestDto;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClosetService {

    private final UserRepository userRepository;
    private final ClothRepository clothRepository;
    private final ClothImageService clothImageService;
    private final ClothCategorizationService clothCategorizationService;

    public List<ClosetGetResponseDto> getAllClothes(String email) {
        // email 과 일치하는 cloth 반환
        User user = userRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("옷 조회 실패"));

        List<Cloth> clothes = clothRepository.findAllByUserEmail(user.getEmail());

        return clothes.stream()
                .map(ClosetGetResponseDto::fromCloset)
                .collect((Collectors.toList()));
    }

    @Transactional
    public void postCloth(String email, ClosetPostRequestDto closetPostRequestDto, MultipartFile clothImg) {
        try {
            User user = userRepository.findById(email)
                    .orElseThrow(() -> new RuntimeException("옷 추가 실패: 없는 유저 email"));

            String url = clothImageService.storeImgToFirebase(clothImg);
            if (url == null) throw new RuntimeException("옷 추가 실패: 이미지 저장 실패");

            Cloth cloth = new Cloth();

            Cloth.builder()
                    .id(UUID.randomUUID())
                    .user(user)
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

            clothRepository.save(cloth);
        } catch (Exception e) {
            throw new RuntimeException("옷 추가 실패" + e.getMessage(), e);
        }
    }

    public ClosetCategorizationResponseDto clothCategorization(MultipartFile clothImg) {
        // file DL 서버에 전송
        // 반환 값 DTO 에 저장

        ClosetCategorizationResponseDto closetCategorizationResponseDto = clothCategorizationService.categorizeCloth(clothImg);
        return closetCategorizationResponseDto;
    }

    public boolean deleteCloth(UUID clothId) {
        if (!clothRepository.existsById(clothId)) return false;

        clothRepository.deleteById(clothId);
        return true;
    }
}
