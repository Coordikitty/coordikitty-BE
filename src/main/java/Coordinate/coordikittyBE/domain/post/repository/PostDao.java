package Coordinate.coordikittyBE.domain.post.repository;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PostDao {
    private final String BUCKET_NAME = "coordikitty.appspot.com";
    public String upload(MultipartFile image, UUID postId) throws IOException {
        String fileName = "postImgs/" + postId + "/" + UUID.randomUUID();
        StorageClient.getInstance().bucket(BUCKET_NAME).create(fileName, image.getInputStream(), image.getContentType());
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", BUCKET_NAME, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    public void delete(UUID postId){
        String fileName = "postImgs/" + postId + "/";
        try {
            Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);
            Page<Blob> blobs = bucket.list(Storage.BlobListOption.prefix(fileName));
            for (Blob blob : blobs.iterateAll()) {
                blob.delete();
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
