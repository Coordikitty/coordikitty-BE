package Coordinate.coordikittyBE.domain.recommend.service;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.recommend.dto.CoordinatesDto;
import Coordinate.coordikittyBE.domain.recommend.dto.RecommendGetResponseDto;
import Coordinate.coordikittyBE.domain.recommend.dto.RecommendRequestDto;
import Coordinate.coordikittyBE.domain.recommend.enums.Type;
import Coordinate.coordikittyBE.domain.recommend.util.WeatherResponse;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class RecommendService {

    private final ClothRepository clothRepository;
    private final UserRepository userRepository;

    @Value("${openweathermap.key}")
    private String apiKey;

    @Value("${domain.ai_server}")
    private String aiServer;

    public List<RecommendGetResponseDto> getRecommend(String email, Type type, String value, CoordinatesDto coordinatesDto) {
        String url = aiServer + "/recommend";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        List<Cloth> clothes = clothRepository.findAllByUserIdAndStyle(user.getId(), Style.valueOf(value));

//        int temperature = getTemperature(coordinatesDto);
        int temperature = 10;   // 날씨 주작
        List<RecommendRequestDto> clothImages = clothes.stream()
                .map(cloth -> RecommendRequestDto.of(cloth, temperature))
                .toList();

        log.info("URL: {}, Type: {}, Value: {}, Coordinates: {}, temperature: {}", url, type, value, coordinatesDto, temperature);

        // type 에 따라 ML 서버랑 통신
        switch (type) {
            //case SITUATION -> {}
            case STYLE -> {
                try {
                    HttpEntity<List<RecommendRequestDto>> request = new HttpEntity<>(clothImages, headers);
                    RestTemplate restTemplate = new RestTemplate();
                    List<RecommendGetResponseDto> response = restTemplate.exchange(
                            url + "/style",
                            HttpMethod.POST,
                            request,
                            new ParameterizedTypeReference<List<RecommendGetResponseDto>>() {
                            }
                    ).getBody();
                    assert response != null;
                    return response;
                } catch (Exception e){
                    log.error("Style: {}", e.getMessage());
                    throw new CoordikittyException(ErrorType.ML_DL_SERVER_ERROR);
                }
            }
            default -> {
                log.error("Default");
                throw new CoordikittyException(ErrorType.ML_DL_SERVER_ERROR);
            }
        }
    }

    public int getTemperature(CoordinatesDto coordinatesDto) {
        // 위치 기반 정보로
        // 날씨 api 에서 날씨 정보 받아오기
        String url = String.format(
                "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s",
                coordinatesDto.latitude(), coordinatesDto.longitude(), apiKey
        );

        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse temperature = restTemplate.getForObject(url, WeatherResponse.class);

        return temperature != null ? (int) Math.round(temperature.getMain().getTemp()) : 30;
    }
}
