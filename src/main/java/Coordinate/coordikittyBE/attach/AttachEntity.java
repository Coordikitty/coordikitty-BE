package Coordinate.coordikittyBE.attach;

import Coordinate.coordikittyBE.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
