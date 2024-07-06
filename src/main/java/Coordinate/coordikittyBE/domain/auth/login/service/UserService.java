package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
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

    public LoginResponseDto signIn(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.email()).orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        if(PasswordUtil.comparePassWord(loginRequestDto.password(), user.getPassword())) {
            TokenDto tokenDto = jwtTokenProvider.generateToken(user);
            refreshTokenRepository.save(RefreshToken.of(user.getEmail(), tokenDto.refreshToken()));
            return LoginResponseDto.of(user, tokenDto);
        }
        throw new CoordikittyException(ErrorType.MEMBER_NOT_FOUND);
    }

    public void logout(String email) {
        refreshTokenRepository.deleteByUserId(email);
    }
}
