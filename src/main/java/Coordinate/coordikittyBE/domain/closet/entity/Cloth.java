package Coordinate.coordikittyBE.domain.closet.entity;

import Coordinate.coordikittyBE.domain.attach.Attach;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.closet.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cloth")
public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "pictureURL", nullable = false)
    private String pictureURL;

    @Column(name = "large", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category.Large large;

    @Column(name = "medium", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category.Medium medium;

    @Column(name = "small", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category.Small small;

    @Column(name = "fit", nullable = false)
    @Enumerated(EnumType.STRING)
    private Fit fit;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "season", nullable = false)
    @Enumerated(EnumType.STRING)
    private Season season;

    @Column(name = "thickness", nullable = false)
    @Enumerated(EnumType.STRING)
    private Thickness thickness;

    @Column(name = "style", nullable = false)
    @Enumerated(EnumType.STRING)
    private Style style;

    @ManyToOne // Many = Cloth, One = User
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cloth")
    private List<Attach> attaches = new ArrayList<>();
}
