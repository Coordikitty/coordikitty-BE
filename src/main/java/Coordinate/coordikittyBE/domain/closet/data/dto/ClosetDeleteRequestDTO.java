package Coordinate.coordikittyBE.domain.closet.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ClosetDeleteRequestDTO {
    private UUID clothId;
}
