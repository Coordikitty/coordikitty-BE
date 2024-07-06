package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LogoutRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
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
    private final RefreshTokenService refreshTokenService;

    public User findById(String email){
        return userRepository.findById(email).orElse(null);
    }

    public LoginResponseDto signIn(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.email()).orElseThrow(()-> new IllegalArgumentException("유저 없음. 회원가입 요망."));
        if(PasswordUtil.comparePassWord(loginRequestDto.password(), user.getPassword())) {
            TokenDto tokenDto = jwtTokenProvider.generateToken(user);
            refreshTokenService.saveRefreshToken(user.getEmail(), tokenDto.refreshToken());

            return LoginResponseDto.of(user.getEmail(), user.getNickname(), tokenDto);
        }
        throw new IllegalArgumentException("비밀번호 불일치");
    }

    public void logout(LogoutRequestDto logoutRequestDto) {
        User user = userRepository.findById(logoutRequestDto.email()).orElseThrow(() -> new IllegalArgumentException("Invalid Email : " + logoutRequestDto.email()));
        refreshTokenRepository.deleteByUserId(user.getEmail());
    }
}
