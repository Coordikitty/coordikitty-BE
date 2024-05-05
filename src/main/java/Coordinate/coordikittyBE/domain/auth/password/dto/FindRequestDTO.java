package Coordinate.coordikittyBE.domain.auth.password.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindRequestDTO {
    private String name;
    private String email;
    private int certifyNumber;
}
