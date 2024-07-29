package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.response.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.request.SocialLoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.request.LoginRequestDto;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.domain.auth.login.util.PasswordUtil;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.global.util.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final JwtHelper jwtHelper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;

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
    @Transactional(readOnly = true)
    public TokenDto reissueToken(String refreshToken){
        if(!refreshTokenService.getRefreshToken(refreshToken)){
            throw new CoordikittyException(ErrorType.TOKEN_NOT_FOUND);
        }
        String newAccessToken = refreshTokenService.reIssueAccessToken(refreshToken);
        String newRefreshToken = refreshTokenService.reIssueRefreshToken(refreshToken);
        return TokenDto.of(newAccessToken, newRefreshToken);
    }

    public LoginResponseDto socialSignIn(SocialLoginRequestDto socialLoginRequestDto) {
        User user = userRepository.findByEmail(socialLoginRequestDto.email())
                .orElseThrow(()-> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
        return grantLoginPermission(user);
    }

    private LoginResponseDto grantLoginPermission(User user){
        String accessToken = jwtHelper.generateAccessToken(user.getEmail(), user.getId());
        String refreshToken = jwtHelper.generateRefreshToken(user.getEmail(), user.getId());

        refreshTokenService.saveRefreshToken(user.getId(), refreshToken);
        return LoginResponseDto.of(user, accessToken, refreshToken);
    }

}
