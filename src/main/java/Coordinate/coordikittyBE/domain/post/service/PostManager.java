package Coordinate.coordikittyBE.domain.post.service;

import Coordinate.coordikittyBE.domain.attach.entity.Attach;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.entity.PostImage;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUpdateRequestDto;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.global.util.FirebaseHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostManager {
    private final PostRepository postRepository;
    private final FirebaseHelper firebaseHelper;

    public void updatePost(PostUpdateRequestDto postUpdateRequestDto, List<Attach> attaches, Post post, UUID postId) {
        List<PostImage> postImages = new ArrayList<>();
        postUpdateRequestDto.postImgs()
            .forEach(img -> postImages.add(PostImage.from(firebaseHelper.uploadPostImage(img, postId), post)));

        post.update(postUpdateRequestDto, attaches, postImages);
    }
}
