package org.example.swaggerexam.dto.shop;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;

@Schema(description = "가게 등록 요청")
public record ShopCreateRequestDto(
        @Schema(description = "가게 이름", example = "멋사 회의실")
        String name,

        @Schema(description = "가게 카테고리", example = "MEETING_ROOM")
        String category,

        @Schema(description = "가게 주소", example = "서울시 마포구 월드컵북로 789")
        String address,

        @Schema(description = "가게 전화번호", example = "02-1111-2222")
        String phone,

        @Schema(description = "가게 설명", example = "프로젝트 회의와 발표 연습이 가능한 공간입니다.")
        String description,

        @Schema(description = "영업 시작 시간", example = "10:00")
        LocalTime openTime,

        @Schema(description = "영업 종료 시간", example = "21:00")
        LocalTime closeTime
) {
}

