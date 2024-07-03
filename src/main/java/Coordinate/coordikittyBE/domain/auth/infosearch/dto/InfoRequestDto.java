package Coordinate.coordikittyBE.domain.auth.infosearch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public record InfoRequestDto(int tall, int weight, int footSize) {
}
