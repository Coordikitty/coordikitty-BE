package Coordinate.coordikittyBE.config.oauth;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final AuthRepository authRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdate(user);
        return user;
    }
    private UserEntity saveOrUpdate(OAuth2User user) {
        Map<String, Object> attributes = user.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        UserEntity userEntity = authRepository.findByEmail(email)
                .map(entity->entity.update(name))
                .orElse(UserEntity.builder().email(email).nickname(name).build());
        return authRepository.save(userEntity);
    }
}
