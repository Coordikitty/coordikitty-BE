package Coordinate.coordikittyBE.domain.auth.signup.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.AuthRepository;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .birth(signUpRequestDto.getBirth())
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .tall(signUpRequestDto.getTall())
                .weight(signUpRequestDto.getWeight())
                .shoeSize(signUpRequestDto.getFootSize())
                .build();
        authRepository.save(user);
    }
}
