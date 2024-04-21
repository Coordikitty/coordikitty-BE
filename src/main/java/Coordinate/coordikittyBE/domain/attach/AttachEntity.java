package Coordinate.coordikittyBE.domain.attach;

import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "attach")
public class AttachEntity {
    @Id
    @Column(name = "attach_id", nullable = false)
    private UUID attachId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    @ManyToOne
    @JoinColumn(name = "cloth_id")
    private ClothEntity clothEntity;
}
