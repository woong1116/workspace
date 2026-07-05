package org.example.swaggerexam.dto.shop;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "가게 목록 응답")
public record ShopListResponseDto(
        @Schema(description = "가게 목록")
        List<ShopResponseDto> shops,

        @Schema(description = "현재 페이지 번호", example = "0")
        int page,

        @Schema(description = "한 페이지에 조회할 개수", example = "10")
        int size,

        @Schema(description = "전체 가게 개수", example = "2")
        Long totalCount
) {
}

