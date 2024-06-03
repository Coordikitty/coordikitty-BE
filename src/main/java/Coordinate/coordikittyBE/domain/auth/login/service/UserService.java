package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginResponseDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.dto.LoginRequestDto;
import Coordinate.coordikittyBE.domain.auth.login.middleware.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    public User findById(String email){
        return userRepository.findById(email).orElse(null);
    }

    public LoginResponseDto signIn(LoginRequestDto loginRequestDto) {
        User user = userRepository.findById(loginRequestDto.getEmail()).orElseThrow(()-> new IllegalArgumentException("유저 없음. 회원가입 요망."));
        TokenDto tokenDto = jwtTokenProvider.generateToken(user);
        RefreshToken refreshTokenInfo = refreshTokenService.findByUserId(user.getEmail());
        if(refreshTokenInfo == null){
            refreshTokenInfo = RefreshToken.of(user.getEmail(), tokenDto.refreshToken());
            refreshTokenService.save(refreshTokenInfo);
            return LoginResponseDto.builder()
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .tokenDto(tokenDto)
                    .build();
        }
        refreshTokenInfo.update(tokenDto.refreshToken());
        refreshTokenService.save(refreshTokenInfo);
        return LoginResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .tokenDto(tokenDto)
                .build();
    }
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findById(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
