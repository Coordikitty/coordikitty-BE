package Coordinate.coordikittyBE.domain.auth.jwtlogin.dto;

import Coordinate.coordikittyBE.domain.closet.enums.Style;

import java.time.LocalDate;
import java.util.List;

public class SignUpRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String name;
    private LocalDate birth;
    private String phoneNumber;
    private int tall;
    private int weight;
    private int footSize;
    private List<Style> preferredStyle;
}
