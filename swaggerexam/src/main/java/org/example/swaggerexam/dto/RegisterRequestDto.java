package org.example.swaggerexam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원가입 요청 DTO")
public class RegisterRequestDto {
    @Schema(description = "사용자 이메일", example = "user@user.com",requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(description = "비밀번호", example = "pass1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
