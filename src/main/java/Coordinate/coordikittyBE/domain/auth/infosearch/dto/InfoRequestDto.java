package Coordinate.coordikittyBE.domain.auth.infosearch.dto;

import lombok.Getter;

@Getter
public record InfoRequestDto(int tall, int weight, int footSize) {
}
