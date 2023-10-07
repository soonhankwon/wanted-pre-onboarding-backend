package dev.wantedpreonboardingbackend.recruitment.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채용공고 수정 요청 DTO")
public record RecruitmentUpdateRequest(
        @Schema(description = "채용포지션", example = "백엔드 주니어 개발자")
        String position,
        @Schema(description = "채용보상금", example = "1500000")
        Long compensation,
        @Schema(description = "채용내용", example = "원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..")
        String description,
        @Schema(description = "사용기술", example = "Python")
        String tech
        ) {
}
