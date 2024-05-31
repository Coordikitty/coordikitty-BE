package Coordinate.coordikittyBE.domain.post.posting.dto;

import Coordinate.coordikittyBE.domain.closet.enums.Season;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostlistResponseDto {
    private UUID postId;
    private Season season;
    private Situation situation;
    private Style style;
    private int postLike;
    private LocalDate uploadDate;
    private String uploaderEmail;
    private String uploaderNickname;
    private String uploaderProfileImg;
    private String thumbnail;
    private Boolean isLiked;
    private Boolean isBookmarked;
}
