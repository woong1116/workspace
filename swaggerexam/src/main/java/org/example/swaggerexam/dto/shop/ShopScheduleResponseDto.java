package org.example.swaggerexam.dto.shop;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "가게 일정 응답")
public record ShopScheduleResponseDto(
        @Schema(description = "일정 ID", example = "1")
        Long id,

        @Schema(description = "일정 제목", example = "백엔드 스터디")
        String title,

        @Schema(description = "일정 날짜", example = "2026-07-01")
        LocalDate scheduleDate,

        @Schema(description = "시작 시간", example = "19:00")
        LocalTime startTime,

        @Schema(description = "종료 시간", example = "21:00")
        LocalTime endTime
) {
}