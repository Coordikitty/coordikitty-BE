package Coordinate.coordikittyBE.domain.recommend.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.closet.util.CategorizedResponse;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import Coordinate.coordikittyBE.domain.recommend.dto.CoordinatesDto;
import Coordinate.coordikittyBE.domain.recommend.dto.RecommendGetResponseDto;
import Coordinate.coordikittyBE.domain.recommend.dto.RecommendRequestDto;
import Coordinate.coordikittyBE.domain.recommend.enums.Type;
import Coordinate.coordikittyBE.domain.recommend.util.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendService {
    
    private final ClothRepository clothRepository;

    @Value("${openweathermap.key}")
    private String apiKey;
    public List<RecommendGetResponseDto> getRecommend(String email, Type type, String value, CoordinatesDto coordinatesDto) {
        String url = "https://2f75-119-201-76-250.ngrok-free.app/recommend";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<Cloth> clothes = clothRepository.findAllByUserEmailAndStyle(email, Style.valueOf(value));
        int temperature = getTemperature(coordinatesDto);
        List<RecommendRequestDto> clothImages = clothes.stream().map((cloth)->
                RecommendRequestDto.of(cloth.getImageUrl(), cloth.getLarge(), cloth.getMedium(), cloth.getStyle(), cloth.getThickness(), temperature))
                .collect(Collectors.toList());
        // type 에 따라 ML 서버랑 통신
        switch (type) {
            //case SITUATION -> {}
            case STYLE -> {
                HttpEntity<List<RecommendRequestDto>> request = new HttpEntity<>(clothImages, headers);
                RestTemplate restTemplate = new RestTemplate();
                List<RecommendGetResponseDto> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        request,
                        new ParameterizedTypeReference<List<RecommendGetResponseDto>>() {}
                ).getBody();
                assert response != null;
                return response;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public int getTemperature(CoordinatesDto coordinatesDto) {
        // 위치 기반 정보로
        // 날씨 api 에서 날씨 정보 받아오기
        String url = String.format(
                "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s",
                coordinatesDto.getLatitude(), coordinatesDto.getLongitude(), apiKey
        );

        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse temperature = restTemplate.getForObject(url, WeatherResponse.class);

        return temperature != null ? (int) Math.round(temperature.getMain().getTemp()) : 30;
    }
}
