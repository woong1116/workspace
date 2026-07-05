package org.example.swaggerexam.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.swaggerexam.dto.shop.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Tag(
        name = "가게 API",
        description = "가게 정보와 가게 일정을 관리하는 API"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Operation(
            summary = "가게 목록 조회",
            description = "등록된 가게 목록을 조회합니다. 카테고리, 검색어, 페이지 정보를 이용해 목록을 조회할 수 있습니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "가게 목록 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShopListResponseDto.class),
                            examples = @ExampleObject(
                                    name = "가게 목록 응답 예시",
                                    value = """
                                            {
                                              "shops": [
                                                {
                                                  "id": 1,
                                                  "name": "멋사 카페",
                                                  "category": "CAFE",
                                                  "address": "서울시 강남구 테헤란로 123",
                                                  "phone": "02-1234-5678"
                                                },
                                                {
                                                  "id": 2,
                                                  "name": "멋사 스터디룸",
                                                  "category": "STUDY_ROOM",
                                                  "address": "서울시 서초구 강남대로 456",
                                                  "phone": "02-9876-5432"
                                                }
                                              ],
                                              "page": 0,
                                              "size": 10,
                                              "totalCount": 2
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다.", content = @Content)
    })
    @GetMapping
    public ResponseEntity<ShopListResponseDto> getShops(
            @Parameter(
                    description = "가게 카테고리",
                    example = "CAFE"
            )
            @RequestParam(required = false) String category,

            @Parameter(
                    description = "검색어",
                    example = "멋사"
            )
            @RequestParam(required = false) String keyword,

            @Parameter(
                    description = "페이지 번호. 0부터 시작합니다.",
                    example = "0"
            )
            @RequestParam(defaultValue = "0") int page,

            @Parameter(
                    description = "한 페이지에 조회할 개수",
                    example = "10"
            )
            @RequestParam(defaultValue = "10") int size
    ) {
        ShopResponseDto shop1 = new ShopResponseDto(
                1L,
                "멋사 카페",
                "CAFE",
                "서울시 강남구 테헤란로 123",
                "02-1234-5678"
        );

        ShopResponseDto shop2 = new ShopResponseDto(
                2L,
                "멋사 스터디룸",
                "STUDY_ROOM",
                "서울시 서초구 강남대로 456",
                "02-9876-5432"
        );

        ShopListResponseDto responseDto = new ShopListResponseDto(
                List.of(shop1, shop2),
                page,
                size,
                2L
        );

        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "가게 단건 조회",
            description = "가게 ID를 이용해 가게 상세 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "가게 단건 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShopDetailResponseDto.class),
                            examples = @ExampleObject(
                                    name = "가게 상세 응답 예시",
                                    value = """
                                            {
                                              "id": 1,
                                              "name": "멋사 카페",
                                              "category": "CAFE",
                                              "address": "서울시 강남구 테헤란로 123",
                                              "phone": "02-1234-5678",
                                              "description": "모임과 스터디를 함께 진행할 수 있는 카페입니다.",
                                              "openTime": "09:00",
                                              "closeTime": "22:00"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다.", content = @Content),
            @ApiResponse(responseCode = "404", description = "가게를 찾을 수 없습니다.", content = @Content)
    })
    @GetMapping("/{shopId}")
    public ResponseEntity<ShopDetailResponseDto> getShop(
            @Parameter(
                    description = "조회할 가게 ID",
                    example = "1",
                    required = true
            )
            @PathVariable Long shopId
    ) {
        ShopDetailResponseDto responseDto = new ShopDetailResponseDto(
                shopId,
                "멋사 카페",
                "CAFE",
                "서울시 강남구 테헤란로 123",
                "02-1234-5678",
                "모임과 스터디를 함께 진행할 수 있는 카페입니다.",
                LocalTime.of(9, 0),
                LocalTime.of(22, 0)
        );

        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "가게 등록",
            description = "새로운 가게 정보를 등록합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "가게 등록 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShopResponseDto.class),
                            examples = @ExampleObject(
                                    name = "가게 등록 응답 예시",
                                    value = """
                                            {
                                              "id": 3,
                                              "name": "멋사 회의실",
                                              "category": "MEETING_ROOM",
                                              "address": "서울시 마포구 월드컵북로 789",
                                              "phone": "02-1111-2222"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "요청 값이 올바르지 않습니다.", content = @Content),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다.", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ShopResponseDto> createShop(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "등록할 가게 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShopCreateRequestDto.class),
                            examples = @ExampleObject(
                                    name = "가게 등록 요청 예시",
                                    value = """
                                            {
                                              "name": "멋사 회의실",
                                              "category": "MEETING_ROOM",
                                              "address": "서울시 마포구 월드컵북로 789",
                                              "phone": "02-1111-2222",
                                              "description": "프로젝트 회의와 발표 연습이 가능한 공간입니다.",
                                              "openTime": "10:00",
                                              "closeTime": "21:00"
                                            }
                                            """
                            )
                    )
            )
            @RequestBody ShopCreateRequestDto requestDto
    ) {
        ShopResponseDto responseDto = new ShopResponseDto(
                3L,
                requestDto.name(),
                requestDto.category(),
                requestDto.address(),
                requestDto.phone()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(
            summary = "가게 정보 수정",
            description = "가게 ID를 이용해 기존 가게 정보를 수정합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "가게 수정 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShopResponseDto.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "요청 값이 올바르지 않습니다.", content = @Content),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다.", content = @Content),
            @ApiResponse(responseCode = "404", description = "가게를 찾을 수 없습니다.", content = @Content)
    })
    @PutMapping("/{shopId}")
    public ResponseEntity<ShopResponseDto> updateShop(
            @Parameter(
                    description = "수정할 가게 ID",
                    example = "1",
                    required = true
            )
            @PathVariable Long shopId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "수정할 가게 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShopUpdateRequestDto.class),
                            examples = @ExampleObject(
                                    name = "가게 수정 요청 예시",
                                    value = """
                                            {
                                              "name": "멋사 카페 강남점",
                                              "category": "CAFE",
                                              "address": "서울시 강남구 테헤란로 321",
                                              "phone": "02-3333-4444",
                                              "description": "스터디와 네트워킹 모임에 적합한 카페입니다.",
                                              "openTime": "08:30",
                                              "closeTime": "23:00"
                                            }
                                            """
                            )
                    )
            )
            @RequestBody ShopUpdateRequestDto requestDto
    ) {
        ShopResponseDto responseDto = new ShopResponseDto(
                shopId,
                requestDto.name(),
                requestDto.category(),
                requestDto.address(),
                requestDto.phone()
        );

        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "가게 삭제",
            description = "가게 ID를 이용해 가게 정보를 삭제합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "가게 삭제 성공", content = @Content),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다.", content = @Content),
            @ApiResponse(responseCode = "404", description = "가게를 찾을 수 없습니다.", content = @Content)
    })
    @DeleteMapping("/{shopId}")
    public ResponseEntity<Void> deleteShop(
            @Parameter(
                    description = "삭제할 가게 ID",
                    example = "1",
                    required = true
            )
            @PathVariable Long shopId
    ) {
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "가게 일정 조회",
            description = "특정 가게에 등록된 일정을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "가게 일정 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShopScheduleListResponseDto.class),
                            examples = @ExampleObject(
                                    name = "가게 일정 응답 예시",
                                    value = """
                                            {
                                              "shopId": 1,
                                              "schedules": [
                                                {
                                                  "id": 1,
                                                  "title": "백엔드 스터디",
                                                  "scheduleDate": "2026-07-01",
                                                  "startTime": "19:00",
                                                  "endTime": "21:00"
                                                },
                                                {
                                                  "id": 2,
                                                  "title": "프로젝트 회의",
                                                  "scheduleDate": "2026-07-02",
                                                  "startTime": "20:00",
                                                  "endTime": "22:00"
                                                }
                                              ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다.", content = @Content),
            @ApiResponse(responseCode = "404", description = "가게를 찾을 수 없습니다.", content = @Content)
    })
    @GetMapping("/{shopId}/schedules")
    public ResponseEntity<ShopScheduleListResponseDto> getShopSchedules(
            @Parameter(
                    description = "일정을 조회할 가게 ID",
                    example = "1",
                    required = true
            )
            @PathVariable Long shopId
    ) {
        ShopScheduleResponseDto schedule1 = new ShopScheduleResponseDto(
                1L,
                "백엔드 스터디",
                LocalDate.of(2026, 7, 1),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0)
        );

        ShopScheduleResponseDto schedule2 = new ShopScheduleResponseDto(
                2L,
                "프로젝트 회의",
                LocalDate.of(2026, 7, 2),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0)
        );

        ShopScheduleListResponseDto responseDto = new ShopScheduleListResponseDto(
                shopId,
                List.of(schedule1, schedule2)
        );

        return ResponseEntity.ok(responseDto);
    }

}
