package dev.wantedpreonboardingbackend.recruitment.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채용공고 지원 요청 DTO")
public record RecruitmentApplyRequest(
        @Schema(description = "사용자 이메일", example = "abc@naver.com")
        String email) {
}
