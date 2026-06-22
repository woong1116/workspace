package jwtexam.security;

import jwtexam.domain.Role;
import jwtexam.domain.User;
import jwtexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("사용자 인증 시도 :: {}",username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                            log.warn("{} 사용자를 찾을 수 없어요.  ", username);
                            return new UsernameNotFoundException("사용자가 없습니다. :: " + username);
                        }
                );
        log.debug("사용자 정보 로드 완료 ");

        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                roles
        );
    }
}
