package Coordinate.coordikittyBE.domain.post.posting.dto.request;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.post.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public record PostUploadRequestDto(
        String content,
        Style style,
        List<UUID> clothIds
        //private List<String> postImgs;
) {

    public static Post toEntity(PostUploadRequestDto postUploadRequestDto, User user){
        return Post.builder()
                .likeCount(0)
                .content(postUploadRequestDto.content())
                .style(postUploadRequestDto.style())
                .user(user)
                .postImgs(new ArrayList<>())
                .attaches(new ArrayList<>())
                .historys(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
