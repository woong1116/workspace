package jwtexam.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenizer {

    private final byte[] accessSecret;
    private final byte[] refreshSecret;
//
//    public final static Long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L; // 30분
//    public final static Long REFRESH_TOKEN_EXPIRE_COUNT = 7 * 24 * 60 * 60 * 1000L; // 7일
    private final long accessExpirationMs;
    private final long refreshExpirationMs;

    public JwtTokenizer(@Value("${jwt.secretKey}") String accessSecret,
                        @Value("${jwt.refreshKey}") String refreshSecret,
                        @Value("${jwt.access-expiration-ms}") long accessExpirationMs,
                        @Value("${jwt.refresh-expiration-ms}")long refreshExpirationMs) {
        this.accessSecret = accessSecret.getBytes(StandardCharsets.UTF_8);
        this.refreshSecret = refreshSecret.getBytes(StandardCharsets.UTF_8);

        this.accessExpirationMs = accessExpirationMs;
        this.refreshExpirationMs = refreshExpirationMs;
    }

    /**
     * Access Token 생성
     */
    public String createAccessToken(Long id, String email, String name,
                                    String username, List<String> roles) {
        return createToken(id, email, name, username, roles,
                accessExpirationMs, accessSecret);
    }

    public String createRefreshToken(Long id, String email, String name,
    String username, List<String> roles) {
        return createToken(id, email, name, username, roles,
                refreshExpirationMs, refreshSecret);
    }


    private String createToken(Long id, String email, String name, String username, List<String> roles, Long expire, byte[] secretKey){
        Date now = new Date();
        Date expiration = new Date(now.getTime()+ expire);

        return Jwts.builder()
                .subject(username) //로그인아이디.
                .claim("email",email)
                .claim("name",name)
                .claim("userId", id)
                .claim("roles",roles)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigingKey(secretKey))
                .compact();
    }

    private SecretKey getSigingKey(byte[] secretKey){
//        new SecretKeySpec(secretKey,"HmacSHA256");
        return Keys.hmacShaKeyFor(secretKey);

    }

    //토큰을 파싱하는 메서드
    private Claims parseToken(String token, byte[] secret){
        token = removeBearerPrefix(token);

        return  Jwts.parser()
                .verifyWith(getSigingKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Claims parseAccessToken(String accessToken){
        return parseToken(accessToken,accessSecret);
    }

    public Claims parseRefreshToken(String refreshToken){
        return parseToken(refreshToken,refreshSecret);
    }

//    토큰에서 id만 꺼내고 싶다.
//    Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJhbWlAZ21haWwuY29tIiwidXNlcm5hbWUiOiJjYXJhbWkiLCJuYW1lIjoi6rCV6rK966-4IiwidXNlcklkIjoxLCJyb2xlcyI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIl0sImlhdCI6MTc4MTc1MDM4NiwiZXhwIjoxNzgxNzUyMTg2fQ.KHE2ij1_cA5Y276TNd96zN1fvYPTKtp5Z3t_f9YPtVg
    public Long getUserIdFromToken(String token){

//        if(token.startsWith("Bearer ")){
//            token = token.substring(7);
//        }
        token = removeBearerPrefix(token);

        Claims claims = parseToken(token, accessSecret);
        return  claims.get("userId",Long.class);
    }


    private String removeBearerPrefix(String token) {

        if(token == null) {
            return null;
        }

        if(token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }

    public Long getAccessTokenExpireCount(){
        return accessExpirationMs;
    }

    public Long getRefreshTokenExpireCount(){
        return refreshExpirationMs;
    }

}
