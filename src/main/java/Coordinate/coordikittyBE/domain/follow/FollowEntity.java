package Coordinate.coordikittyBE.domain.follow;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "follow")
public class FollowEntity {
    @Id
    @Column(name = "follow_id", nullable = false)
    private UUID followId;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private UserEntity fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private UserEntity toUser;
}
