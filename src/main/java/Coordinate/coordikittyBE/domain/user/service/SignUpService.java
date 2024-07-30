package Coordinate.coordikittyBE.domain.user.service;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.domain.user.dto.SignUpRequestDto;
import Coordinate.coordikittyBE.domain.user.dto.SignUpResponseDto;
import Coordinate.coordikittyBE.domain.user.dto.SignUpSocialRequestDto;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        if(userRepository.existsByEmail(signUpRequestDto.email())){
            throw new CoordikittyException(ErrorType.DUPLICATED_EMAIL_ERROR);
        }
        return SignUpResponseDto.fromEntity(userRepository.save(SignUpRequestDto.toEntity(signUpRequestDto)));
    }
    public User signUpSocial(SignUpSocialRequestDto signUpSocialRequestDto){
        if(userRepository.existsByEmail(signUpSocialRequestDto.email())){
            throw new CoordikittyException(ErrorType.DUPLICATED_EMAIL_ERROR);
        }
        return userRepository.save(SignUpSocialRequestDto.toEntity(signUpSocialRequestDto));
    }
}
