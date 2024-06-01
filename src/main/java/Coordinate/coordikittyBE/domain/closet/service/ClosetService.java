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
            User user = userRepository.findById(email)
                    .orElseThrow(() -> new RuntimeException("옷 추가 실패: 없는 유저 email"));

            String url = clothImageService.storeImgToFirebase(clothImg);
            if (url == null) throw new RuntimeException("옷 추가 실패: 이미지 저장 실패");

            clothRepository.save(Cloth.of(closetPostRequestDto, user, url));
    }

    public ClosetCategorizationResponseDto clothCategorization(MultipartFile clothImg) {


        ClosetCategorizationResponseDto closetCategorizationResponseDto = clothCategorizationService.categorizeCloth(clothImg);
        return closetCategorizationResponseDto;
    }

    public boolean deleteCloth(UUID clothId) {
        if (!clothRepository.existsById(clothId)) return false;

        clothRepository.deleteById(clothId);
        return true;
    }
}
