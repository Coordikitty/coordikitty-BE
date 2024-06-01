package Coordinate.coordikittyBE.domain.attach;

import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
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
@Entity(name = "attach")
public class Attach {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "cloth_id")
    private Cloth cloth;

    public static Attach of(Cloth cloth, Post post) {
        return Attach.builder()
                .id(UUID.randomUUID())
                .cloth(cloth)
                .post(post)
                .build();
    }
}