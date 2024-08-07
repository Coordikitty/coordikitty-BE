package Coordinate.coordikittyBE.domain.history.entity;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "is_Bookmarked", nullable = false)
    @Builder.Default
    private Boolean isBookmarked = false;

    @Column(name = "is_Liked", nullable = false)
    @Builder.Default
    private Boolean isLiked = false;

    public void toggleBookmarked() {
        this.isBookmarked = !this.isBookmarked;
    }

    public void toggleLike(){
        this.isLiked = !this.isLiked;
    }
    public static History of(User user, Post post) {
        return History.builder()
                .user(user)
                .post(post)
                .isBookmarked(false)
                .isLiked(false)
                .build();
    }
}
