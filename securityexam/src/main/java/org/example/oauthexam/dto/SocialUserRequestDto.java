package org.example.oauthexam.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.oauthexam.entity.Role;
import org.example.oauthexam.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class SocialUserRequestDto {
    private String provider;
    private String socialId;
    private String name;
    private String email;
    private String username;
    private String uuid;

    public User toSocialEntity(PasswordEncoder passwordEncoder, Set<Role> defaultRoles) {
        String encodedPassword = passwordEncoder.encode(UUID.randomUUID().toString());

        return User.builder()
                .username(this.username)
                .name(this.name)
                .email(this.email)
                .socialId(this.socialId)
                .provider(this.provider)
                .password(encodedPassword)
                .build();
    }
}
