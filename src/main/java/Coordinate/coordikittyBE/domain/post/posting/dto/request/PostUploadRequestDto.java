package Coordinate.coordikittyBE.domain.post.posting.dto.request;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostUploadRequestDto {
    private String content;
    private Style style;
    //private List<String> postImgs;
    private List<UUID> clothIds;

    public static Post toEntity(PostUploadRequestDto postUploadRequestDto, User user){
        return Post.builder()
                .id(UUID.randomUUID())
                .likeCount(0)
                .content(postUploadRequestDto.getContent())
                .style(postUploadRequestDto.getStyle())
                .user(user)
                .postImgs(new ArrayList<>())
                .attaches(new ArrayList<>())
                .historys(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
