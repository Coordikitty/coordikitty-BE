package Coordinate.coordikittyBE.domain.auth.signup.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpResponseDto;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpSocialRequestDto;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        if(userRepository.existsById(signUpRequestDto.email())){
            throw new CoordikittyException(ErrorType.DUPLICATED_EMAIL_ERROR);
        }
        return SignUpResponseDto.fromEntity(userRepository.save(SignUpRequestDto.toEntity(signUpRequestDto)));
    }
    public User signUpSocial(SignUpSocialRequestDto signUpSocialRequestDto){
        if(userRepository.existsById(signUpSocialRequestDto.email())){
            throw new CoordikittyException(ErrorType.DUPLICATED_EMAIL_ERROR);
        }
        return userRepository.save(SignUpSocialRequestDto.toEntity(signUpSocialRequestDto));
    }
}
