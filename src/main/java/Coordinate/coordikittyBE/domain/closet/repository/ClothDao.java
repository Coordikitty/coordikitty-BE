package Coordinate.coordikittyBE.domain.closet.repository;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClothDao {
    private final String BUCKET_NAME = "coordikitty.appspot.com";
    public String upload(MultipartFile image, UUID clothId) throws IOException {
        String fileName = "clothes/" + clothId + "/" + clothId;
        StorageClient.getInstance().bucket(BUCKET_NAME).create(fileName, image.getInputStream(), image.getContentType());
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", BUCKET_NAME, fileName);
    }

    public void delete(UUID clothId, String email) {
        String fileName = "clothes/" + clothId + "/" + clothId;
        StorageClient.getInstance().bucket(BUCKET_NAME).get(fileName).delete();
    }

}
