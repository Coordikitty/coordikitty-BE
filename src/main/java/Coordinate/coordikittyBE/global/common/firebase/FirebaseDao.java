package Coordinate.coordikittyBE.global.common.firebase;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FirebaseDao {
    String UPLOAD_LINK = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media";
    String BUCKET_NAME = "coordikitty.appspot.com";
    String upload(MultipartFile image, UUID postId);
    void delete(UUID postId);
}
