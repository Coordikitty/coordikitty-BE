package Coordinate.coordikittyBE.domain.auth.signup.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.util.PasswordUtil;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public record SignUpRequestDto(
        String email,
        String password,
        String nickname,
        String name,
        int year,
        int month,
        int day,
        String phoneNumber,
        int tall,
        int weight,
        int footSize,
        List<Style> preferredStyle
) {
    public static User toEntity(SignUpRequestDto signUpRequestDto){
        return User.builder()
                .email(signUpRequestDto.email())
                .password(PasswordUtil.encodePassWord(signUpRequestDto.password()))
                .name(signUpRequestDto.name())
                .nickname(signUpRequestDto.nickname())
                .birth(LocalDate.of(signUpRequestDto.year(), signUpRequestDto.month(), signUpRequestDto.day()))
                .phoneNumber(signUpRequestDto.phoneNumber())
                .tall(signUpRequestDto.tall())
                .weight(signUpRequestDto.weight())
                .shoeSize(signUpRequestDto.footSize())
                .build();
    }
}
