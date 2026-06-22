package org.example.basicjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Slf4j
public class JwtExample {
    public static void main(String[] args) {
//        시크릿키 생성
//        방법 1
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println("랜덤 시크릿 키:: " +secretKey);

//        방법 2 - 내가 정해준 문자열을 바탕으로 생성함.  (문자열이 같으면 같은 시크릿키를 얻어옴.)
        String secret = "abcdefghijklmnopqrstuvwxzy123456";
        SecretKey secretKey2 = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        System.out.println("시크릿키 :: "+ secretKey2);

//        JWT 생성
        String jwt = Jwts.builder()
                .issuer("lion_auth") //토근 발급 주체
                .subject("kang")   //username - 로그인 id
                .audience().add("lion-server").and()  // 누구를 위한 것인지
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))  //토큰 만료시간 3600초  1시간
                .issuedAt(new Date())  //토큰 발급시간
                .notBefore(new Date())
                .claim("role", "USER")
                .signWith(secretKey2)
                .compact();

        System.out.println(jwt);

        Claims claims = Jwts.parser()
                .verifyWith(secretKey2)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();


        log.info("=== JWT 검증 성공 ===");
        log.info("발급자: {}", claims.getIssuer());
        log.info("사용자 ID: {}", claims.getSubject());
        log.info("대상: {}", claims.getAudience());
        log.info("발급 시간: {}", claims.getIssuedAt());
        log.info("만료 시간: {}", claims.getExpiration());
        log.info("역할: {}", claims.get("role", String.class));


//
        SecretKey key = Jwts.SIG.HS256.key().build();
        String keys = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(keys);
    }

}
