package Coordinate.coordikittyBE.domain.security.jwt;

import Coordinate.coordikittyBE.exception.ErrorType;
import Coordinate.coordikittyBE.global.common.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");

        log.info("jwtExceptionFilter 실행");
        try{
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e){
            log.info("expiredJwtEcxeption");
            response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(ErrorType.TOKEN_EXPIRED)));
        } catch (JwtException e){
            log.info("JwtException");
            response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(ErrorType.TOKEN_NOT_FOUND)));
        }
    }
}
