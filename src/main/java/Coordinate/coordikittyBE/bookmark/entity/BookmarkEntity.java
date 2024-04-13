package Coordinate.coordikittyBE.bookmark.entity;

import Coordinate.coordikittyBE.auth.entity.UserEntity;
import Coordinate.coordikittyBE.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "bookmark")
public class BookmarkEntity {
    @Id
    @Column(name = "bookmark_id", nullable = false)
    private UUID bookmarkId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

}
