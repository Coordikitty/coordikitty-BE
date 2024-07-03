package Coordinate.coordikittyBE.domain.auth.signup.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpSocialRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    public void signUp(SignUpRequestDto signUpRequestDto) {
        User user = SignUpRequestDto.toEntity(signUpRequestDto);
        userRepository.save(user);
    }
    public void signUpSocial(SignUpSocialRequestDto signUpSocialRequestDto){
        User user = SignUpSocialRequestDto.fromEntity(signUpSocialRequestDto);
        userRepository.save(user);
    }
}
