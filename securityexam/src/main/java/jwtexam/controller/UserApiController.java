package jwtexam.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jwtexam.domain.RefreshToken;
import jwtexam.domain.Role;
import jwtexam.domain.User;
import jwtexam.dto.UserLoginResponseDto;
import jwtexam.jwt.util.JwtTokenizer;
import jwtexam.security.dto.UserLoginDto;
import jwtexam.service.RefreshTokenService;
import jwtexam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDto userLoginDto,
                                   BindingResult bindingResult,
                                   HttpServletResponse response){
//        jwt를 이용해서 로그인할때 사용자(클라이언트)는 어떤 정보들을 보내줄까요??
//1. 사용자가 입력한 값이 유효한가?
        if(bindingResult.hasErrors()){
            //유효성검증 실패.  이랬을때는 오류메시지 바디에 담아 넘겨줘요.
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

//        2. 사용자가 입력한 정보가 시스템이 가진 정보와 동일 한가??  username 으로 user 존재하나?
        User user = userService.findByUsername(userLoginDto.getUsername()).orElse(null);
        if(user == null || !passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            // user가 존재하지 않거나,  비밀번호가 일치하지 않는다면 401 반환!!
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디나 비밀번호가 올바르지 않습니다.");
        }
//상태유지를 위한 부분!!!

        //3. 토큰 발급
        //3-1. 토큰 발급을 위해 Role 정보 추출
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        //3-2. 각각 토큰 발급
        String accessToken = jwtTokenizer.createAccessToken(user.getId(), user.getEmail(), user.getName(), user.getUsername(), roles);
        String refreshToken = jwtTokenizer.createRefreshToken(user.getId(), user.getEmail(), user.getName(), user.getUsername(), roles);

//        4. 리프레시토큰 DB저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setUserId(user.getId());

        refreshTokenService.addRefreshToken(refreshTokenEntity);

//        토큰을 쿠키에 구워보내든지, 응답에 실어 보내든지..
        addTokenCookie("accessToken", accessToken, jwtTokenizer.getAccessTokenExpireCount(), response);
        addTokenCookie("refreshToken", refreshToken, jwtTokenizer.getRefreshTokenExpireCount(), response);

        //응답에 accessToken, refreshToken, userId, userName

        UserLoginResponseDto responseDto = UserLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .name(user.getName())
                .build();


        return ResponseEntity.ok(responseDto);
    }


    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request,HttpServletResponse response){
//        1. 쿠키에서 리프레시 토큰을 추출
        String token = getRefreshToken(request);

//        2. 토큰이 없다면??  오류 발생 (400)
        if(token == null){
            return ResponseEntity.badRequest().body("리프레시토큰이 없어요.");
        }

        try {

//        3. 토큰을 검증 및 파싱  - 맞지않다면 적절하게 예외처리
            Claims claims = jwtTokenizer.parseRefreshToken(token);
//        4. 우리 서버에 저장된 리프레시토큰과 사용자가 가져온 리프레시토큰이 일치할때만!! 액세스토큰 재발급!! - 없다면,같지않다면. 적절하게 오류처리
            RefreshToken dbToken = refreshTokenService.findRefreshToken(token).orElse(null);
            if(dbToken == null || !token.equals(dbToken.getToken())){
                log.warn("사용자가 보낸 리프레시토큰이 DB 에 저장된 토큰과 다릅니다.");

//               이때 DB에서 토큰을 삭제한다든지..  각 서비스별로 정한 정책에 따라서 해야할 일이 있다면 구현!!
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("리프레시 토큰이 이상해요.");
            }
//        5. 사용자 정보 추출
            Long userId = claims.get("userId", Long.class);
            User user = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾지 못했습니다."));

//        6. 새로운 액세스토큰 생성  (액세스토큰을 갱신하는 것!!  )
            List<String> roles = claims.get("roles", List.class);
            String accessToken = jwtTokenizer.createAccessToken(userId, user.getEmail(), user.getName(), user.getUsername(), roles);

//        7. 새로 생성된 액세스토큰을 쿠키에 넣거나,
            addTokenCookie("accessToken",accessToken,jwtTokenizer.getAccessTokenExpireCount(),response);

//        8.응답으로 보냄..
            UserLoginResponseDto responseDto = UserLoginResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(token)
                    .name(user.getName())
                    .userId(user.getId())
                    .build();

            return ResponseEntity.ok(responseDto);
        }catch (MalformedJwtException | IllegalArgumentException e){
            //잘못된 토큰 처리!!
            log.error("Invalid refresh Token : {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh Token");
        }catch (ExpiredJwtException e){
            //만료된 토큰이라면?
            log.warn("Expried refresh Token : {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 만료됨.");
        }
        catch (Exception e) {
            //기타오류
            log.error("refresh token error : {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Internal server error");
        }

    }

    //refreshToken 쿠키에서 토큰을 추출하는 메서드
    private String getRefreshToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie:cookies){
                if("refreshToken".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void addTokenCookie(String cookieName, String cookieValue, Long expireCount, HttpServletResponse response){
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");
        cookie.setHttpOnly(true);  //자바스크립트는 이 쿠키에 접근 불가능!!
        cookie.setMaxAge(Math.toIntExact(expireCount/1000));
//        cookie.setSecure(true);  //htts를 사용한다면..  필요함!!
        response.addCookie(cookie);
    }

}
