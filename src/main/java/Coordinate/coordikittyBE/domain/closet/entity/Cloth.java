package Coordinate.coordikittyBE.domain.closet.entity;

import Coordinate.coordikittyBE.domain.attach.entity.Attach;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.closet.dto.request.ClosetPostRequestDto;
import Coordinate.coordikittyBE.domain.closet.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name = "cloth")
public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "thickness", nullable = false)
    @Enumerated(EnumType.STRING)
    private Thickness thickness;

    @Column(name = "style", nullable = false)
    @Enumerated(EnumType.STRING)
    private Style style;

    @ManyToOne(fetch = FetchType.LAZY) // Many = Cloth, One = User
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cloth", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Attach> attaches = new ArrayList<>();

    public static Cloth of(ClosetPostRequestDto closetPostRequestDto, User user){
        return Cloth.builder()
                .user(user)
                .large(closetPostRequestDto.large())
                .medium(closetPostRequestDto.medium())
                .small(closetPostRequestDto.small())
                .fit(closetPostRequestDto.fit())
                .gender(closetPostRequestDto.gender())
                .style(closetPostRequestDto.style())
                .thickness(closetPostRequestDto.thickness())
                .build();
    }

    public void addImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
