package Coordinate.coordikittyBE.domain.history.entity;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.post.entity.Post;
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
@Entity(name = "history")
public class History {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "is_Bookmarked", nullable = false)
    private Boolean isBookmarked;

    @Column(name = "is_Liked", nullable = false)
    private Boolean isLiked;

    public static History of(User user, Post post) {
        return History.builder()
                .id(UUID.randomUUID())
                .user(user)
                .post(post)
                .isBookmarked(false)
                .isLiked(false)
                .build();
    }
}
