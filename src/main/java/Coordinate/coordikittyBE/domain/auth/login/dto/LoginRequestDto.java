package Coordinate.coordikittyBE.domain.auth.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record LoginRequestDto(String email, String password) {
}
