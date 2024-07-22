package Coordinate.coordikittyBE.domain.closet.service;

import Coordinate.coordikittyBE.domain.attach.repository.AttachRepository;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.dto.response.ClosetCategorizationResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.response.ClosetGetResponseDto;
import Coordinate.coordikittyBE.domain.closet.dto.request.ClosetPostRequestDto;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothDao;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.closet.util.CategorizedResponse;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
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

@RequiredArgsConstructor
@Service
public class ClosetService {

    private final UserRepository userRepository;
    private final ClothRepository clothRepository;
    private final ClothDao clothDao;
    private final AttachRepository attachRepository;

    @Transactional
    public List<ClosetGetResponseDto> getAllClothes(String email) {
        return clothRepository.findAllByUserId(
                    userRepository.findByEmail(email)
                        .orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND))
                        .getId()
                )
                .stream()
                .map(ClosetGetResponseDto::fromEntity)
                .toList();
    }

    @Transactional
    public String postCloth(String email, ClosetPostRequestDto closetPostRequestDto, MultipartFile clothImg) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        Cloth cloth = Cloth.of(closetPostRequestDto, user);

        cloth.addImageUrl(clothDao.upload(clothImg, cloth.getId()));
        clothRepository.save(cloth);
        return "업로드 성공";
    }

    public ClosetCategorizationResponseDto clothCategorization(MultipartFile clothImg){
        String url = "http://localhost:8000/categorization";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = getMultiValueMapHttpEntity(clothImg, headers);

        RestTemplate restTemplate = new RestTemplate();
        CategorizedResponse response = restTemplate.postForObject(url, request, CategorizedResponse.class);
        assert response != null;
        return ClosetCategorizationResponseDto.fromDL(response);
    }

    @Transactional
    public void deleteCloth(UUID clothId) {
        attachRepository.deleteAllByClothId(clothId);
        clothRepository.deleteById(clothId);
        clothDao.delete(clothId);
    }

    private static HttpEntity<MultiValueMap<String, Object>> getMultiValueMapHttpEntity(MultipartFile clothImg, HttpHeaders headers) {
        ByteArrayResource resource;
        try {
            resource = new ByteArrayResource(clothImg.getBytes()) {
                @Override
                public String getFilename() {
                    return clothImg.getOriginalFilename();
                }
            };
        } catch (IOException e) {
            throw new CoordikittyException(ErrorType.TRANSFORT_MULTIFILE_ERROR);
        }

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);

        return new HttpEntity<>(body, headers);
    }
}
