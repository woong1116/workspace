package org.example.swaggerexam.dto.shop;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

@Schema(description = "가게 상세 응답")
public record ShopDetailResponseDto(
        @Schema(description = "가게 ID", example = "1")
        Long id,

        @Schema(description = "가게 이름", example = "멋사 카페")
        String name,

        @Schema(description = "가게 카테고리", example = "CAFE")
        String category,

        @Schema(description = "가게 주소", example = "서울시 강남구 테헤란로 123")
        String address,

        @Schema(description = "가게 전화번호", example = "02-1234-5678")
        String phone,

        @Schema(description = "가게 설명", example = "모임과 스터디를 함께 진행할 수 있는 카페입니다.")
        String description,

        @Schema(description = "영업 시작 시간", example = "09:00")
        LocalTime openTime,

        @Schema(description = "영업 종료 시간", example = "22:00")
        LocalTime closeTime
) {
}