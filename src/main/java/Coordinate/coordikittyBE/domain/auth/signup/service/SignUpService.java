package Coordinate.coordikittyBE.domain.auth.signup.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpResponseDto;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpSocialRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = SignUpRequestDto.toEntity(signUpRequestDto);
        userRepository.save(user);
        return SignUpResponseDto.fromEntity(user);
    }
    public void signUpSocial(SignUpSocialRequestDto signUpSocialRequestDto){
        User user = SignUpSocialRequestDto.toEntity(signUpSocialRequestDto);
        userRepository.save(user);
    }
}
