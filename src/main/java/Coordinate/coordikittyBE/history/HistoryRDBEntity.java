package Coordinate.coordikittyBE.history;

import Coordinate.coordikittyBE.auth.entity.UserEntity;
import Coordinate.coordikittyBE.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "history")
public class HistoryRDBEntity {
    @Id
    @Column(name = "history_id", nullable = false)
    private UUID historyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    @Column(name = "isBookmarked", nullable = false)
    private Boolean isBookmarked;

    @Column(name = "isLiked", nullable = false)
    private Boolean isLiked;
}
