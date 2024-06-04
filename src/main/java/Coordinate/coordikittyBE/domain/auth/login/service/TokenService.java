package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public LoginResponseDto createNewAccessToken(String refreshToken) {
        RefreshToken refreshInfo= refreshTokenService.findByRefreshToken(refreshToken);
        if(refreshInfo == null){
            throw new IllegalArgumentException("Invalid refresh token");
        }
        User user = userService.findById(refreshInfo.getUserId());
        TokenDto token = jwtTokenProvider.generateToken(user);
        refreshInfo.update(token.refreshToken());
        refreshTokenService.save(refreshInfo);

        return LoginResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .tokenDto(token)
                .build();
    }
}
