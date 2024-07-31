package Coordinate.coordikittyBE.domain.closet.repository;

import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.global.common.firebase.FirebaseDao;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Qualifier("clothDao")
public class ClothDao implements FirebaseDao {
    @Override
    public String upload(MultipartFile image, UUID clothId) {
        try {
            String fileName = "clothes/" + clothId + "/" + clothId;
            StorageClient.getInstance().bucket(BUCKET_NAME).create(fileName, image.getInputStream(), image.getContentType());
            return String.format(UPLOAD_LINK, BUCKET_NAME, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        } catch(IOException e){
            throw new CoordikittyException(ErrorType.FIREBASE_ERROR);
        }
    }

    @Override
    public void delete(UUID clothId) {
        String fileName = "clothes/" + clothId + "/" + clothId;
        StorageClient.getInstance().bucket(BUCKET_NAME).get(fileName).delete();
    }

}
