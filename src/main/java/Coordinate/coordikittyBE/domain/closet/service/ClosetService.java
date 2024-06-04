package Coordinate.coordikittyBE.domain.closet.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetCategorizationResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.ClosetPostRequestDto;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothDao;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.closet.util.CategorizedResponse;
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
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClosetService {

    private final UserRepository userRepository;
    private final ClothRepository clothRepository;
    private final ClothDao clothDao;

    public List<ClosetGetResponseDto> getAllClothes(String email) {
        // email 과 일치하는 cloth 반환

        List<Cloth> clothes = clothRepository.findAllByUserEmail(email);

        return clothes.stream()
                .map(ClosetGetResponseDto::fromCloset)
                .collect((Collectors.toList()));
    }

    @Transactional
    public String postCloth(String email, ClosetPostRequestDto closetPostRequestDto, MultipartFile clothImg) throws IOException {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("옷 추가 실패: 없는 유저 email"));
        Cloth cloth = Cloth.of(closetPostRequestDto, user);
        String imageUrl = clothDao.upload(clothImg, cloth.getId());
        cloth.addImageUrl(imageUrl);
        clothRepository.save(cloth);
        return "업로드 성공";
    }

    public ClosetCategorizationResponseDto clothCategorization(MultipartFile clothImg) throws IOException {
        String url = "http://localhost:8000/categorization";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        ByteArrayResource resource;
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
        CategorizedResponse response = restTemplate.postForObject(url, request, CategorizedResponse.class);
        assert response != null;
        return ClosetCategorizationResponseDto.fromDL(response);
    }

    @Transactional
    public void deleteCloth(UUID clothId, String email) {
        clothRepository.deleteById(clothId);
        clothRepository.flush();
        clothDao.delete(clothId, email);
    }
}
