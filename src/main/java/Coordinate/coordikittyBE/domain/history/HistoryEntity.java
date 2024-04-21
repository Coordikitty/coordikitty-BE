package Coordinate.coordikittyBE.domain.history;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "history")
public class HistoryEntity {
    @Id
    @Column(name = "history_id", nullable = false)
    private UUID historyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    @Column(name = "is_Bookmarked", nullable = false)
    private Boolean isBookmarked;

    @Column(name = "is_Liked", nullable = false)
    private Boolean isLiked;
}
