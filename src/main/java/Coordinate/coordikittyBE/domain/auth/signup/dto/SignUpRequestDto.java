package Coordinate.coordikittyBE.domain.auth.signup.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.util.PasswordUtil;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    public static User toEntity(SignUpRequestDto signUpRequestDto){
        return User.builder()
                .email(signUpRequestDto.getEmail())
                .password(PasswordUtil.encodePassWord(signUpRequestDto.getPassword()))
                .name(signUpRequestDto.getName())
                .nickname(signUpRequestDto.getNickname())
                .birth(LocalDate.of(signUpRequestDto.getYear(), signUpRequestDto.getMonth(), signUpRequestDto.getDay()))
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .tall(signUpRequestDto.getTall())
                .weight(signUpRequestDto.getWeight())
                .shoeSize(signUpRequestDto.getFootSize())
                .build();
    }
}
