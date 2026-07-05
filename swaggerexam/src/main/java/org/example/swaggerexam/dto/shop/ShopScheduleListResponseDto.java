package org.example.swaggerexam.dto.shop;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "가게 일정 목록 응답")
public record ShopScheduleListResponseDto(
        @Schema(description = "가게 ID", example = "1")
        Long shopId,

        @Schema(description = "일정 목록")
        List<ShopScheduleResponseDto> schedules
) {
}