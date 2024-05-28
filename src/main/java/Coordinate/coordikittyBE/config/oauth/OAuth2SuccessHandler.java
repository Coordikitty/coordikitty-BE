package Coordinate.coordikittyBE.config.oauth;

import Coordinate.coordikittyBE.domain.auth.entity.RefreshToken;
import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;
import Coordinate.coordikittyBE.domain.auth.login.middleware.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.login.service.UserService;
import Coordinate.coordikittyBE.domain.auth.login.util.CookieUtil;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignUpSocialRequestDto;
import Coordinate.coordikittyBE.domain.auth.signup.service.SignUpService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
@Slf4j
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final String REDIRECT_URI = "/post";

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final UserService userService;
    private final SignUpService signUpService;

    public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");
        UserEntity user = userService.findById(email);
        if(user==null){
            signUpService.signUpSocial(new SignUpSocialRequestDto(email));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"email\": \"" + email + "\"}");
            return;
        }
        TokenDto token = jwtTokenProvider.generateToken(user);
        String refreshToken = token.getRefreshToken();

        saveRefreshToken(user.getEmail(), refreshToken);
        addRefreshTokenToCookie(request, response, refreshToken);

        String accessToken = token.getAccessToken();
        clearAuthenticationAttributes(request, response);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"accessToken\": \"" + accessToken+ "\", \"refreshToken\": \"" + refreshToken + "\"}");
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    private void saveRefreshToken(String email, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(email)
                .map(entity->entity.update(newRefreshToken))
                .orElse(new RefreshToken(email, newRefreshToken));
        refreshTokenRepository.save(refreshToken);
    }

}
