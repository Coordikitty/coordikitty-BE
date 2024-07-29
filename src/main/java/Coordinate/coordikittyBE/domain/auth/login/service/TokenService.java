package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.domain.security.jwt.JwtTokenProvider;
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
    private final UserRepository userRepository;

    public LoginResponseDto createNewAccessToken(String refreshToken) {
        RefreshToken refreshInfo= refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new CoordikittyException(ErrorType.TOKEN_NOT_FOUND));
        User user = userRepository.findById(refreshInfo.getUserId()).orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        TokenDto token = jwtTokenProvider.generateToken(user);
        refreshInfo.update(token.refreshToken());

        return LoginResponseDto.of(user, token);
    }
}
