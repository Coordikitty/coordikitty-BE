package Coordinate.coordikittyBE.security.oauth;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.security.jwt.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpSocialRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.service.SignUpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final SignUpService signUpService;

    public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");
        User user = userRepository.findById(email).orElse(null);
        if(user==null){
            signUpService.signUpSocial(SignUpSocialRequestDto.from(email));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"email\": \"" + email + "\"}");
            return;
        }
        TokenDto token = jwtTokenProvider.generateToken(user);
        String refreshToken = token.refreshToken();

        RefreshToken newRefreshToken = refreshTokenRepository.findByUserId(email)
                .map(entity->entity.update(refreshToken))
                .orElse(RefreshToken.of(email, refreshToken));

        refreshTokenRepository.save(newRefreshToken);

        String accessToken = token.accessToken();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"accessToken\": \"" + accessToken+ "\", \"refreshToken\": \"" + refreshToken + "\"}");
    }



}
