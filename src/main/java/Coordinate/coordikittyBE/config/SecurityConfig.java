package Coordinate.coordikittyBE.config;

import Coordinate.coordikittyBE.config.oauth.CustomAuthenticationEntryPoint;
import Coordinate.coordikittyBE.config.oauth.OAuth2SuccessHandler;
import Coordinate.coordikittyBE.config.oauth.OAuth2UserCustomService;
import Coordinate.coordikittyBE.config.jwt.JwtAuthenticationFilter;
import Coordinate.coordikittyBE.config.jwt.JwtTokenProvider;
import Coordinate.coordikittyBE.domain.auth.login.service.RefreshTokenService;
import Coordinate.coordikittyBE.domain.auth.login.service.UserService;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import Coordinate.coordikittyBE.domain.auth.signup.service.SignUpService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;
    private final SignUpService signUpService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(
                        new AntPathRequestMatcher("/img/**"),
                        new AntPathRequestMatcher("/css/**"),
                        new AntPathRequestMatcher("/js/**")
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)

                .httpBasic(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(oAuth2UserCustomService))
                        .successHandler(oAuth2SuccessHandler())
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(logout->logout.logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/auth/signUp",
                                "/auth/signUp/dupCheck",
                                "/auth/login",
                                "/auth/token",
                                "/auth/login/google",
                                "/oauth2/authorization/google",
                                "/post/unLogged",
                                "/post/get/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint))
                .build();
    }

    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler(){
        return new OAuth2SuccessHandler(
                jwtTokenProvider,
                refreshTokenRepository,
                userService,
                signUpService
        );
    }

    @Bean
    public JwtAuthenticationFilter tokenAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }

}
