package org.example.swaggerexam.dto.shop;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

@Schema(description = "가게 수정 요청")
public record ShopUpdateRequestDto(
        @Schema(description = "가게 이름", example = "멋사 카페 강남점")
        String name,

        @Schema(description = "가게 카테고리", example = "CAFE")
        String category,

        @Schema(description = "가게 주소", example = "서울시 강남구 테헤란로 321")
        String address,

        @Schema(description = "가게 전화번호", example = "02-3333-4444")
        String phone,

        @Schema(description = "가게 설명", example = "스터디와 네트워킹 모임에 적합한 카페입니다.")
        String description,

        @Schema(description = "영업 시작 시간", example = "08:30")
        LocalTime openTime,

        @Schema(description = "영업 종료 시간", example = "23:00")
        LocalTime closeTime
) {
}