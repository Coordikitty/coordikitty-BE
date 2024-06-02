package Coordinate.coordikittyBE.domain.post.posting.service;


import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUploadRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostConverter {
    public PostResponseDto toDto(Post post){
        return PostResponseDto.builder()
                .postId(post.getId())
                .style(post.getStyle())
                .postLike(post.getLikeCount())
                .uploadDate(post.getCreatedAt())
                .uploaderEmail(post.getUser().getEmail())
                .uploaderNickname(post.getUser().getNickname())
                .uploaderProfileImg(post.getUser().getProfileUrl())
                //.postImgs(post.getPostImgs())
                .isLiked(true)
                .isBookmarked(true)
                .build();
    }

    public Post fromDto(PostUploadRequestDto postUploadRequestDto, User user){
        return Post.builder()
                .id(UUID.randomUUID())
                .likeCount(0)
                .content(postUploadRequestDto.getContent())
                .style(postUploadRequestDto.getStyle())
                .user(user)
                .postImgs(new ArrayList<>())
                .bookmarks(new ArrayList<>())
                .attaches(new ArrayList<>())
                .historys(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }
}
