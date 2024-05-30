package Coordinate.coordikittyBE.domain.auth.signup.dto;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String name;
    private int year;
    private int month;
    private int day;
    private String phoneNumber;
    private int tall;
    private int weight;
    private int footSize;
    private List<Style> preferredStyle;
}
