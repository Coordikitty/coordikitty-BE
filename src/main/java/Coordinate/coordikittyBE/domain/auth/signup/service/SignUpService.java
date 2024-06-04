package Coordinate.coordikittyBE.domain.auth.signup.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.util.PasswordUtil;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpSocialRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    public void signUp(SignUpRequestDto signUpRequestDto) {
        User user = User.builder()
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
        userRepository.save(user);
    }
    public void signUpSocial(SignUpSocialRequestDto signUpSocialRequestDto){
        User user = User.builder()
                .email(signUpSocialRequestDto.getEmail())
                .password(UUID.randomUUID().toString())
                .build();
        userRepository.save(user);
    }
}
