package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.SocialLoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpSocialRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.service.SignUpService;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.security.jwt.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.login.util.PasswordUtil;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService{
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final SignUpService signUpService;
    public LoginResponseDto signIn(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.email()).orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        if(PasswordUtil.comparePassWord(loginRequestDto.password(), user.getPassword())) {
            TokenDto tokenDto = jwtTokenProvider.generateToken(user);
            refreshTokenRepository.save(RefreshToken.of(user.getEmail(), tokenDto.refreshToken()));
            return LoginResponseDto.of(user, tokenDto);
        }
        throw new CoordikittyException(ErrorType.MEMBER_NOT_FOUND);
    }

    public String logout(String email) {
        refreshTokenRepository.deleteByUserId(email);
        return "Logout success";
    }

    public LoginResponseDto socialSignIn(SocialLoginRequestDto socialLoginRequestDto) {
        User user = userRepository.findByEmail(socialLoginRequestDto.email())
                .orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        TokenDto tokenDto = jwtTokenProvider.generateToken(user);
        refreshTokenRepository.save(RefreshToken.of(user.getEmail(), tokenDto.refreshToken()));
        return LoginResponseDto.of(user, tokenDto);
    }


}
