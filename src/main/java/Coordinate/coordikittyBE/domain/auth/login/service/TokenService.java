package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.config.jwt.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    public LoginResponseDto createNewAccessToken(String refreshToken) {
        RefreshToken refreshInfo= refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new IllegalArgumentException("토큰 오류"));
        User user = userService.findById(refreshInfo.getUserId());
        TokenDto token = jwtTokenProvider.generateToken(user);
        refreshInfo.update(token.refreshToken());

        return LoginResponseDto.of(user.getEmail(), user.getNickname(), token);
    }
}
