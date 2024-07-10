package Coordinate.coordikittyBE.domain.settings.image.repository;

import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import com.google.cloud.storage.StorageException;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Repository
@RequiredArgsConstructor
public class ImageDao {
    private final String BUCKET_NAME = "coordikitty.appspot.com";
    public String upload(MultipartFile image, String email) {
        try {
            String fileName = "image/" + email + "/" + email;
            StorageClient.getInstance().bucket(BUCKET_NAME).create(fileName, image.getInputStream(), image.getContentType());
            return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", BUCKET_NAME, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        } catch(IOException e){
            throw new CoordikittyException(ErrorType.FIREBASE_ERROR);
        }
    }

    public void delete(String email) {
        String fileName = "image/" + email + "/" + email;
        try {
            StorageClient.getInstance().bucket(BUCKET_NAME).get(fileName).delete();
        } catch (Exception e) {
            throw new CoordikittyException(ErrorType.FIREBASE_ERROR);
        }
    }
}
