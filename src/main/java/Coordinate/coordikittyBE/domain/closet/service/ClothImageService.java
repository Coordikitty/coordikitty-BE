package Coordinate.coordikittyBE.domain.closet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ClothImageService {

    public String storeImgToFirebase(MultipartFile clothImg) {
        // firebase 연결해서 clothImg 저장 후 url 반환
        // 저장 실패 시 null 반환

        return "firebase.storage.url";
    }
}
