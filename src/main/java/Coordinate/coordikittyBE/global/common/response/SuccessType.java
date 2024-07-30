package Coordinate.coordikittyBE.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessType {
    EXAMPLE_SUCCESS("성공 메세지");
    private final String message;
}
