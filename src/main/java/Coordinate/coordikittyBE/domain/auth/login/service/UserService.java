package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.middleware.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    public UserEntity findById(String email){
        return authRepository.findById(email).orElse(null);
    }

    public TokenDto signIn(LoginRequestDto loginRequestDto) {
        UserEntity user = authRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()-> new IllegalArgumentException("Unexpected User"));
        TokenDto tokenDto = jwtTokenProvider.generateToken(user);
        RefreshToken refreshTokenInfo = refreshTokenService.findByUserId(user.getEmail());
        if(refreshTokenInfo != null){
            refreshTokenInfo.update(tokenDto.getRefreshToken());
            refreshTokenService.save(refreshTokenInfo);
            return tokenDto;
        }
        return tokenDto;
    }
}
