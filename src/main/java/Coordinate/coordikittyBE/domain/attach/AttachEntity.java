package Coordinate.coordikittyBE.domain.attach;

import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
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
@Entity(name = "attach")
public class AttachEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID attachId;

    @ManyToOne
    @JoinColumn(name = "attach_post_id")
    private PostEntity postEntity;

    @ManyToOne
    @JoinColumn(name = "attach_cloth_id")
    private ClothEntity clothEntity;

    public static AttachEntity of(ClothEntity cloth, PostEntity post) {
        return AttachEntity.builder()
                .attachId(UUID.randomUUID())
                .clothEntity(cloth)
                .postEntity(post)
                .build();
    }
}
