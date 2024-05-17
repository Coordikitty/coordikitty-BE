package Coordinate.coordikittyBE.domain.closet.entity;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.closet.enums.*;
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
@Entity(name = "cloth")
public class ClothEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID clothId;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "pictureURL", nullable = false)
    private String pictureURL;

    @Column(name = "large", nullable = false)
    private Large large;

    @Column(name = "medium", nullable = false)
    private String medium;

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
    @JoinColumn(name = "cloth_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "attach_cloth_id")
    private UUID attachId;
}
