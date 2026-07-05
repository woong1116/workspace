package org.example.swaggerexam.dto.shop;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "가게 기본 응답")
public record ShopResponseDto(
        @Schema(description = "가게 ID", example = "1")
        Long id,

        @Schema(description = "가게 이름", example = "멋사 카페")
        String name,

        @Schema(description = "가게 카테고리", example = "CAFE")
        String category,

        @Schema(description = "가게 주소", example = "서울시 강남구 테헤란로 123")
        String address,

        @Schema(description = "가게 전화번호", example = "02-1234-5678")
        String phone
) {
}