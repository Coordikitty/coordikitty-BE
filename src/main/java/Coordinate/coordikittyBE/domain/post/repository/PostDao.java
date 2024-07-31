package Coordinate.coordikittyBE.domain.post.repository;

import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.global.common.firebase.FirebaseDao;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
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
@Qualifier("postDao")
public class PostDao implements FirebaseDao {
    @Override
    public String upload(MultipartFile image, UUID postId) {
        try {
            String fileName = "postImgs/" + postId + "/" + UUID.randomUUID();
            StorageClient.getInstance().bucket(BUCKET_NAME).create(fileName, image.getInputStream(), image.getContentType());
            return String.format(UPLOAD_LINK, BUCKET_NAME, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        }
        catch(IOException e){
            throw new CoordikittyException(ErrorType.FIREBASE_ERROR);
        }
    }

    @Override
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
            throw new CoordikittyException(ErrorType.FIREBASE_ERROR);
        }
    }

}
