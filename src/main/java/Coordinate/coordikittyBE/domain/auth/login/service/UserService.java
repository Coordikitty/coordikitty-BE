package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LogoutRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
import Coordinate.coordikittyBE.config.jwt.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.login.util.PasswordUtil;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    public User findById(String email){
        return userRepository.findById(email).orElse(null);
    }

    public LoginResponseDto signIn(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.getEmail()).orElseThrow(()-> new IllegalArgumentException("유저 없음. 회원가입 요망."));
        if(PasswordUtil.comparePassWord(loginRequestDto.getPassword(), user.getPassword())) {
            TokenDto tokenDto = jwtTokenProvider.generateToken(user);
            RefreshToken refreshTokenInfo = refreshTokenService.findByUserId(user.getEmail());

            if (refreshTokenInfo == null) {
                refreshTokenInfo = RefreshToken.of(user.getEmail(), tokenDto.refreshToken());
                refreshTokenService.save(refreshTokenInfo);
                return LoginResponseDto.of(user.getEmail(), user.getNickname(), tokenDto);
            }
            refreshTokenInfo.update(tokenDto.refreshToken());
            refreshTokenService.save(refreshTokenInfo);
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
