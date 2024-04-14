package Coordinate.coordikittyBE.follow;

import Coordinate.coordikittyBE.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
