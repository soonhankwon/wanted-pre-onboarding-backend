package dev.wantedpreonboardingbackend.recruitment.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채용공고 목록 조회 응답 DTO")
public record RecruitmentGetResponse(
        @Schema(description = "채용공고_id", example = "1")
        Long recruitmentId,
        @Schema(description = "회사명", example = "원티드랩")
        String companyName,
        @Schema(description = "국가", example = "한국")
        String nation,
        @Schema(description = "지역", example = "서울")
        String area,
        @Schema(description = "채용포지션", example = "백엔드 주니어 개발자")
        String position,
        @Schema(description = "채용보상금", example = "1500000")
        Long compensation,
        @Schema(description = "사용기술", example = "Python")
        String requiredTech
) {
}
