package Coordinate.coordikittyBE.domain.search.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SearchResponseDto {
    private int postId;
    private int postLike;
    private LocalDateTime uploadDate;
    private String uploaderEmail;
    private String uploaderNickname;
    private String uploaderProfileImg;
    private String thumbnail;
    private Boolean isLiked;
    private Boolean isBookmarked;
}