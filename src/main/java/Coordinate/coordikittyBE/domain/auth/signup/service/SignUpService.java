package Coordinate.coordikittyBE.domain.auth.signup.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.AuthRepository;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpSocialRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final AuthRepository authRepository;
    public void signUp(SignUpRequestDto signUpRequestDto) {
        UserEntity user = UserEntity.builder()
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .name(signUpRequestDto.getName())
                .nickname(signUpRequestDto.getNickname())
                .birth(LocalDate.of(signUpRequestDto.getYear(), signUpRequestDto.getMonth(), signUpRequestDto.getDay()))
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .tall(signUpRequestDto.getTall())
                .weight(signUpRequestDto.getWeight())
                .shoeSize(signUpRequestDto.getFootSize())
                .build();
        authRepository.save(user);
    }
    public void signUpSocial(SignUpSocialRequestDto signUpSocialRequestDto){
        UserEntity user = UserEntity.builder()
                .email(signUpSocialRequestDto.getEmail())
                .password(UUID.randomUUID().toString())
                .build();
        authRepository.save(user);
    }
}
