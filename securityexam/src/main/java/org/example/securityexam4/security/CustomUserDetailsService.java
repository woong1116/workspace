package org.example.securityexam4.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.securityexam4.domain.Role;
import org.example.securityexam4.domain.User;
import org.example.securityexam4.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException(username+"에 해당하는 사용가자 없습니다.");

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());

//        시큐리티는 Role 의 이름을 반드시   ROLE_ 로 시작되길 원해요.  ROLE_USER,  ROLE_ADMIN
        userBuilder.roles(
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .toArray(String[]::new)
        );
//      아래 메서드를 이용해서 Role을 채우면 ROLE_ 가 붙지 않는다.  DB에 이미 ROLE_USER, ROLE_ADMIN  이렇게 저장되어있어야함.
//        userBuilder.authorities(
//
//        )
        return userBuilder.build();
    }
}
