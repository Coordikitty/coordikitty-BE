package Coordinate.coordikittyBE.domain.auth.signup.dto;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDTO {
    private String email;
    private String password;
    private String nickname;
    private String name;
    private Date birth;
    private String phoneNumber;
    private int tall;
    private int weight;
    private int footSize;
    private List<Style> preferredStyle;
}
