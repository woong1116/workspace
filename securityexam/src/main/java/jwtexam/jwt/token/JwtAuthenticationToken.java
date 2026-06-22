package jwtexam.jwt.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private Object principal;  //사용자 정보 (UserDtails)
    private Object credentials;  //인증자격정보 (보통은 비밀번호,  jwt기반으로 인증할때는 null)

    //인증이 완료된 후 호출되어야함!!
    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials){
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);   // 인증완료!!
    }

    //인증되기전 사용되는 생성자
    public JwtAuthenticationToken(String token){
        super(null);
        this.principal = null;
        this.credentials=token;
        setAuthenticated(false);
    }
    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
