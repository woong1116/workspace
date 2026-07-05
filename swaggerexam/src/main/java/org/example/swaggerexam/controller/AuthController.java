package org.example.swaggerexam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.swaggerexam.dto.LoginRequestDto;
import org.example.swaggerexam.dto.LoginResponseDto;
import org.example.swaggerexam.dto.RegisterRequestDto;
import org.example.swaggerexam.service.UserService;
import org.example.swaggerexam.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Autentication",description = "인증 관련 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @Operation(
            summary = "환영인사",
            description = "환영인사를 합니다."
    )
    @GetMapping("/welcome")
    @SecurityRequirement(name="bearerAuth")
    public String welcome(@RequestHeader("Authorization")String authorizationHearder){
        //"Bearer " 제거하고 사용해야함.
        if(authorizationHearder != null) {
            String token = authorizationHearder.substring(7);
            if(token != null){
                return "welcome";
            }

        }
        return "인증 후 볼 수 있어요.";
    }
    @Operation(
            summary = "테스트",
            description = "동작이 잘 되는지 테스트 합니다."
    )
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @Operation(
            summary = "회원가입",
            description = "사용자가 이메일과 비밀 번호를 입력하면 회원 가입합니다."
    )
//    응답에 관련된 시나리오를 설명
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                content = @Content(mediaType = "text/plain",
                    schema = @Schema(type = "String", example = "회원가입 성공")
                )
            ),
            @ApiResponse(responseCode = "400", description = "회원가입 실패",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "String", example = "회원가입 실패")
                    )
            )
    }
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto requestDto){


        String message = userService.register(requestDto.getEmail(), requestDto.getPassword());
        if("존재하는 아이디 입니다.".equals(message))
            return ResponseEntity.status(400).body(message);
        return ResponseEntity.ok(message);
    }
    @Operation(
            summary = "로그인",
            description = "사용자 로그인 정보를 받아 JWT 토큰을 발급합니다."
    )
    @SecurityRequirements
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "로그인 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDto.class),
                            examples = @ExampleObject(
                                    name = "로그인 성공 응답",
                                    value = """
                                            {
                                              "token": "eyJhbGciOiJIUzI1NiJ9..."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "요청 값이 올바르지 않음",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "아이디 또는 비밀번호가 올바르지 않음",
                    content = @Content
            )
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "로그인 요청 정보",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = LoginRequestDto.class),
                            examples = @ExampleObject(
                                    name = "로그인 요청 예시",
                                    value = """
                                            {
                                              "email": "user@example.com",
                                              "password": "1234"
                                            }
                                            """
                            )
                    )
            )
            @RequestBody LoginRequestDto requestDto){
        //우리는 그냥 1L 아이디가 로그인하는것으로 하드코딩하는데..  (나중에 여러분이 시스템이 가진 정보와 연결해보세요.)
        Long userId=1L;

        //jwt토큰을 발행
        String token = jwtUtil.generateToken(userId);

        //이 토큰을 응답결과로 보내주면 되겠죠??
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @Operation(summary = "로그아웃")
    @SecurityRequirement(name="bearerAuth")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @Parameter(description = "JWT 인증 토큰", required = true, example = "Bearer eyJhbGciOiJIUzI....")
            @RequestHeader("Authorization")String authorizationHearder){
        //"Bearer " 제거하고 사용해야함.
        String token = authorizationHearder.substring(7);
        jwtUtil.invalidateToken(token);
        return ResponseEntity.ok("로그아웃 성공");
    }

}
