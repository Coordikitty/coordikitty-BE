package Coordinate.coordikittyBE.domain.settings.profile.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingProfileResponseDto {
    private String name;
    private String number;
    private String nickname;

    public static SettingProfileResponseDto from(User user) {
        return SettingProfileResponseDto.builder()
                .name(user.getName())
                .number(user.getPhoneNumber())
                .nickname(user.getNickname())
                .build();
    }
}
