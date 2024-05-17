package Coordinate.coordikittyBE.domain.bookmark.entity;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "bookmark")
public class BookmarkEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID bookmarkId;

    @ManyToOne
    @JoinColumn(name = "bookmark_user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "bookmark_post_id")
    private PostEntity postEntity;

}
