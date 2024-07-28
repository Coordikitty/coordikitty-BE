package Coordinate.coordikittyBE.domain.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Builder
    public RefreshToken(UUID userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public static RefreshToken of(UUID userId, String refreshToken) {
        return new RefreshToken(userId, refreshToken);
    }

    public RefreshToken update(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
