package Coordinate.coordikittyBE.domain.closet.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetCategorizationResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetPostRequestDto;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.closet.util.RecommendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public ClosetCategorizationResponseDto clothCategorization(MultipartFile clothImg) throws IOException {
        String url = "http://localhost:8000/categorization";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(clothImg.getBytes()) {
                @Override
                public String getFilename() {
                    return clothImg.getOriginalFilename();
                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        RecommendResponse response = restTemplate.postForObject(url, request, RecommendResponse.class);

        System.out.println(response);

        assert response != null;
        return ClosetCategorizationResponseDto.fromDL(response);
    }

    public boolean deleteCloth(UUID clothId) {
        if (!clothRepository.existsById(clothId))
            return false;
        else {
            clothRepository.deleteById(clothId);
            return true;
        }
    }
}
