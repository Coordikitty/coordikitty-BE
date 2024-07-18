package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.request.SocialLoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginRequestDto;
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
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginResponseDto signIn(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.email())
                .orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        if(PasswordUtil.comparePassWord(loginRequestDto.password(), user.getPassword())) {
            return grantLoginPermission(user);
        }
        throw new CoordikittyException(ErrorType.MEMBER_NOT_FOUND);
    }

    public String logout(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        refreshTokenRepository.deleteByUserId(user.getId());
        return "Logout success";
    }

    public LoginResponseDto socialSignIn(SocialLoginRequestDto socialLoginRequestDto) {
        User user = userRepository.findByEmail(socialLoginRequestDto.email())
                .orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        return grantLoginPermission(user);
    }

    private LoginResponseDto grantLoginPermission(User user){
        RefreshToken refreshTokenInfo = refreshTokenRepository.findByUserId(user.getId())
                .orElseThrow(() -> new CoordikittyException(ErrorType.INVALID_USER_ID));
        TokenDto tokenDto = jwtTokenProvider.generateToken(user);
        if(refreshTokenInfo==null) {
            refreshTokenRepository.save(RefreshToken.of(user.getId(), tokenDto.refreshToken()));
            return LoginResponseDto.of(user, tokenDto);
        }
        refreshTokenInfo.update(tokenDto.refreshToken());
        return LoginResponseDto.of(user, tokenDto);
    }

}
