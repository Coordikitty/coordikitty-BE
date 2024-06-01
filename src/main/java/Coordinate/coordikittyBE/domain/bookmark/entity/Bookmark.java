package Coordinate.coordikittyBE.domain.bookmark.entity;

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
@Entity(name = "bookmark")
public class Bookmark {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    public static Bookmark of(User user, Post post) {
        return Bookmark.builder()
                .id(UUID.randomUUID())
                .user(user)
                .post(post)
                .build();
    }
}
