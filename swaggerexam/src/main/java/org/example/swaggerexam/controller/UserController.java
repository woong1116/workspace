package org.example.swaggerexam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.swaggerexam.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Autentication",description = "인증 관련 API")
@RestController
@RequestMapping("/users")
public class UserController {
    //사용자 목록  - users
    @Operation(summary = "사용자 목록 조회", description = "사용자 목록을 페이지 단위로 조회합니다.")
    @SecurityRequirement(name = "bearerAuth") // "bearerAuth" 보안 설정 적용
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        // ...
        return ResponseEntity.ok(null);
    }

    //id에 해당하는 사용자 조회  - users/{id}
    @Operation(summary = "사용자 정보 조회", description = "사용자의 고유 ID를 이용하여 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable("id") Long id) {
//        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(null);
    }
}
