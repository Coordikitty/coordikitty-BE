package Coordinate.coordikittyBE.domain.closet.service;

import Coordinate.coordikittyBE.domain.closet.dto.ClosetCategorizationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ClothCategorizationService {

    public ClosetCategorizationResponseDto categorizeCloth(MultipartFile clothImg) {
        // DL 서버랑 통신
        // Req: cloth Img
        // Res: 옷 분류, fit, gender, style

        ClosetCategorizationResponseDto response = new ClosetCategorizationResponseDto();

//        response.fromDL();

        return response;
    }
}
