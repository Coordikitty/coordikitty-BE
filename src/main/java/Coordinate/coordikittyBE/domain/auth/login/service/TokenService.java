package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.middleware.JwtTokenProvider;
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
        User user = userService.findById(refreshInfo.getUserId());
        TokenDto token = jwtTokenProvider.generateToken(user);

        refreshInfo.update(token.getRefreshToken());
        refreshTokenService.save(refreshInfo);

        return token;
    }
}
