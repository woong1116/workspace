package jwtexam.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final String name;
    private final String email;
    private final  List<GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, String name, String email, List<String> roles){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role)) //ROLE_USER,ROLE_ADMIN
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
