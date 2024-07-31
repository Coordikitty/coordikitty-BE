package Coordinate.coordikittyBE.global.util;

import Coordinate.coordikittyBE.global.common.firebase.FirebaseDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class FirebaseHelper {
    private final FirebaseDao postDao;
    private final FirebaseDao clothDao;

    public FirebaseHelper(
            @Qualifier("postDao") FirebaseDao postDao,
            @Qualifier("clothDao") FirebaseDao clothDao
    ){
        this.postDao = postDao;
        this.clothDao = clothDao;
    }

    public String uploadPostImage(MultipartFile image, UUID postId){
        return postDao.upload(image, postId);
    }

    public void deletePostImage(UUID postId){
        postDao.delete(postId);
    }

    public String uploadClothImage(MultipartFile image, UUID clothId){
        return clothDao.upload(image, clothId);
    }

    public void deleteClothImage(UUID clothId){
        clothDao.delete(clothId);
    }
}
