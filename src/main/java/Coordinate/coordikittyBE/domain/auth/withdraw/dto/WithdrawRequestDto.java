package Coordinate.coordikittyBE.domain.auth.withdraw.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public record WithdrawRequestDto(String email) {
}
