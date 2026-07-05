package jwtexam.jwt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwtexam.jwt.exception.JwtExceptionCode;
import jwtexam.jwt.token.JwtAuthenticationToken;
import jwtexam.jwt.util.JwtTokenizer;
import jwtexam.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//OncePerRequestFilter 전체 요청중에 단 한 번만 실행되요.
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        이 필터는 무슨일을 해야할까요?
//       1. 요청에 포함되어온 JWT(AccessToken) 을 검증
//       2. 토큰이 유효하다면? - 시큐리티 컨텍스트 홀더에 인증정보(Authentication) 를 등록한다.

        //1. 토큰을 얻어온다.
        String token = getToken(request);

        //2. 토큰을 파싱하고, 필요한 정보를 찾아서...  Authentication 객체를 만들어서 시큐리티컨텍스트홀더에 넣어주는일!
        if(StringUtils.hasText(token)){
            try{
                getAuthentication(token);
            }catch (ExpiredJwtException e){
                request.setAttribute("exception", JwtExceptionCode.EXPIRED_TOKEN.getCode());
                log.error("Expried Token : {} ", token,e);
                throw  new BadCredentialsException("Expired token exception",e);
            }catch (UnsupportedJwtException e){
                request.setAttribute("exception", JwtExceptionCode.UNSUPPORTED_TOKEN.getCode());
                log.error("Unsupported Token: {}", token, e);
                throw new BadCredentialsException("Unsupported token exception", e);
            } catch (MalformedJwtException e) {
                request.setAttribute("exception", JwtExceptionCode.INVALID_TOKEN.getCode());
                log.error("Invalid Token: {}", token, e);
                throw new BadCredentialsException("Invalid token exception", e);
            } catch (IllegalArgumentException e) {
                request.setAttribute("exception", JwtExceptionCode.NOT_FOUND_TOKEN.getCode());
                log.error("Token not found: {}", token, e);
                throw new BadCredentialsException("Token not found exception", e);
            }catch (Exception e){
                log.error("JWT Filter - Internal Error : {}", token,e);
                throw new BadCredentialsException("JWT Filter internal exception ",e);
            }
        }


        //중요해요!!!
        filterChain.doFilter(request,response);


    }
    private void getAuthentication(String token){
        Claims claims = jwtTokenizer.parseAccessToken(token);
        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);
        String name = claims.get("name", String.class);
        String email = claims.get("email", String.class);
        List<GrantedAuthority> authorities = getAuthorities(claims);

//        이것을 시큐리티가 사용할 수 있게 넣어야겠죠??
        CustomUserDetails customUserDetails = new CustomUserDetails(username,"",name,email,
                authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(authority->authority.replace("ROLE_",""))
                        .collect(Collectors.toList())
        );

        //시큐리티 컨텍스트 홀더에 저장할 수 있도록 Authentication 객체를 생성하면 됨.
        Authentication authentication = new JwtAuthenticationToken(authorities, customUserDetails, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);


    }
//    권한 정보를 List<String> 으로 받아오는데 시큐리티는 List<GrantedAuthority> 형태로 사용하기를 원함. (변환필요!!)
    private List<GrantedAuthority> getAuthorities(Claims claims){
        List<String> roles = (List<String>) claims.get("roles");
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return  authorities;
    }

    private String getToken(HttpServletRequest request){
        //헤더 정보에서 accessToken을 찾는다면???
        String authorization = request.getHeader("Authorization");
        if(StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")){
            return authorization.substring(7);
        }


//       accessToken이 쿠키를 통해서 들어왔다면??
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie:cookies){
                if("accessToken".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
