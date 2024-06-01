package Coordinate.coordikittyBE.domain.follow.enity;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "follow")
public class Follow {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;
}
