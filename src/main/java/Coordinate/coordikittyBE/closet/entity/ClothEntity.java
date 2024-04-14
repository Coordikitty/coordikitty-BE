package Coordinate.coordikittyBE.closet.entity;

import Coordinate.coordikittyBE.attach.AttachEntity;
import Coordinate.coordikittyBE.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cloth")
public class ClothEntity {
    @Id
    @Column(name = "cloth_id", nullable = false)
    private UUID clothId;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "pictureURL", nullable = false)
    private String pictureURL;

    @ManyToOne  // Many = Cloth, One = User
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "clothEntity")
    private List<AttachEntity> attachEntities;
}
