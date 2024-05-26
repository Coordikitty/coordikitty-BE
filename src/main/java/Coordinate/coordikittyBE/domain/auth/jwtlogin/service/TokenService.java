package Coordinate.coordikittyBE.domain.auth.jwtlogin.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.jwtlogin.middleware.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public TokenDto createNewAccessToken(String refreshToken) {
        RefreshToken refreshInfo= refreshTokenService.findByRefreshToken(refreshToken);
        if(refreshInfo == null){
            throw new IllegalArgumentException("Invalid refresh token");
        }
        UserEntity user = userService.findById(refreshInfo.getUserId());
        TokenDto token = jwtTokenProvider.generateToken(user);

        refreshInfo.update(token.getRefreshToken());
        refreshTokenService.save(refreshInfo);

        return token;
    }
}
