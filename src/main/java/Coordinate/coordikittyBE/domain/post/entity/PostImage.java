package Coordinate.coordikittyBE.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name = "postImage")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public static PostImage create(String imageUrl, Post post) {
        return PostImage.builder()
                .imageUrl(imageUrl)
                .post(post)
                .build();
    }
}
