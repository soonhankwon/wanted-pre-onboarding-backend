package dev.wantedpreonboardingbackend.recruitment.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "채용공고 상세페이지 조회 응답 DTO")
public record RecruitmentDetailGetResponse(
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
        String requiredTech,
        @Schema(description = "채용내용", example = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
        String description,
        @Schema(description = "회사가올린다른채용공고", example = "[3, 4, ..]")
        List<Long> relatedRecruitmentsByCompany
) {
}
