package Coordinate.coordikittyBE.domain.closet.repository;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
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
    public void upload(MultipartFile image, String email, UUID clothId) throws IOException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(email).document(clothId.toString());
        Map<String, Object> data = new HashMap<>();
        data.put("image", Arrays.toString(image.getBytes()));
        docRef.set(data);
    }
}
