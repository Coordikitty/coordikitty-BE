package Coordinate.coordikittyBE.domain.recommend.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import Coordinate.coordikittyBE.domain.recommend.dto.CoordinatesDto;
import Coordinate.coordikittyBE.domain.recommend.dto.RecommendGetResponseDto;
import Coordinate.coordikittyBE.domain.recommend.util.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendService {

    private final UserRepository userRepository;
    private final ClothRepository clothRepository;

    @Value("${openweathermap.key}")
    private String apiKey;

    public List<RecommendGetResponseDto> getRecommend(String email, Situation situation, CoordinatesDto coordinatesDto) {
        // Situation 추천 ML 에 user id, situation 전송
        // 코디 = 옷 List 반환

        User user = userRepository.findById(email).orElseThrow(() -> new RuntimeException("User not found"));
        List<Cloth> clothes = clothRepository.findAllByUserEmail(user.getEmail());

        // situation 추천 ML 에 사용자가 가진 옷 리스트 전송
        // 추천 옷 리스트 반환 -> recommendedClothes
        // List<Cloth> recommendedClothes = new ArrayList<>();

        Integer temperature = getTemperature(coordinatesDto);

        return clothes.stream()
                .map(RecommendGetResponseDto::fromCloth)
                .collect(Collectors.toList());
    }

    public List<RecommendGetResponseDto> getRecommend(String email, Style style, CoordinatesDto coordinatesDto) {
        // Style 추천 ML 에 user id, style 전송
        // 코디 = 옷 List 반환

        User user = userRepository.findById(email).orElseThrow(() -> new RuntimeException("User not found"));
        List<Cloth> clothes = clothRepository.findAllByUserEmail(user.getEmail());

        // style 추천 ML 에 사용자가 가진 옷 리스트 전송
        // 추천 옷 리스트 반환 -> recommendedClothes
        // List<Cloth> recommendedClothes = new ArrayList<>();

        Integer temperature = getTemperature(coordinatesDto);

        return clothes.stream()
                .map(RecommendGetResponseDto::fromCloth)
                .collect(Collectors.toList());
    }

    public Integer getTemperature(CoordinatesDto coordinatesDto) {
        // 위치 기반 정보로
        // 날씨 api 에서 날씨 정보 받아오기
        String url = String.format(
                "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s",
                coordinatesDto.getLatitude(), coordinatesDto.getLongitude(), apiKey
        );

        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse temperature = restTemplate.getForObject(url, WeatherResponse.class);

        return temperature != null ? (int) Math.round(temperature.getMain().getTemp()) : null;
    }
}
