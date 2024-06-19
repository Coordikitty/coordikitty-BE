package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LogoutRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
import Coordinate.coordikittyBE.config.jwt.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.login.util.PasswordUtil;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    public User findById(String email){
        return userRepository.findById(email).orElse(null);
    }

    public LoginResponseDto signIn(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.email()).orElseThrow(()-> new IllegalArgumentException("유저 없음. 회원가입 요망."));
        if(PasswordUtil.comparePassWord(loginRequestDto.password(), user.getPassword())) {
            TokenDto tokenDto = jwtTokenProvider.generateToken(user);
            RefreshToken refreshTokenInfo = refreshTokenRepository.findByUserId(user.getEmail())
                    .orElseGet(()->{
                        return RefreshToken.of(user.getEmail(), tokenDto.refreshToken());
                    });
            refreshTokenInfo.update(tokenDto.refreshToken());
            refreshTokenRepository.save(refreshTokenInfo);
            return LoginResponseDto.of(user.getEmail(), user.getNickname(), tokenDto);
        }
        throw new IllegalArgumentException("비밀번호 불일치");
    }

    public void logout(LogoutRequestDto logoutRequestDto) {
        User user = userRepository.findById(logoutRequestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid Email : " + logoutRequestDto.getEmail()));
        if (refreshTokenService.findByRefreshToken(logoutRequestDto.getRefreshToken()) != null) {
            // refreshToken 삭제
            refreshTokenService.removeRefreshToken(user.getEmail());
            return;
        }
        throw new IllegalArgumentException("Invalid RefreshToken : " + logoutRequestDto.getRefreshToken());
    }
}
