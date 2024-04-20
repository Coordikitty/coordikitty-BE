package Coordinate.coordikittyBE.closet.entity;

import Coordinate.coordikittyBE.attach.AttachEntity;
import Coordinate.coordikittyBE.auth.entity.UserEntity;
import Coordinate.coordikittyBE.closet.enums.*;
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

    @Column(name = "large", nullable = false)
    private Large large;

    @Column(name = "medium", nullable = false)
    private Medium medium;

    @Column(name = "small", nullable = false)
    private Small small;

    @Column(name = "fit", nullable = false)
    private Fit fit;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "season", nullable = false)
    private Season season;

    @Column(name = "color", nullable = false)
    private Color color;

    @Column(name = "style", nullable = false)
    private Style style;

    @ManyToOne  // Many = Cloth, One = User
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "clothEntity")
    private List<AttachEntity> attachEntities;
}
