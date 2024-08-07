package Coordinate.coordikittyBE.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name = "tags")
public class Tags  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "formal")
    private int formal;

    @Column(name = "minimalistic")
    private int minimalistic;

    @Column(name = "casual")
    private int casual;

    @Column(name = "street")
    private int street;

    @Column(name = "sports")
    private int sports;

    // User Entity 에 Tags Entity id OneToOne 추가 필요
}
