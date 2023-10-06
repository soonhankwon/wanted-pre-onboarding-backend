package dev.wantedpreonboardingbackend.recruitment.controller.dto;

public record RecruitmentGetResponse(
        Long recruitmentId,
        String companyName,
        String nation,
        String area,
        String position,
        Long compensation,
        String requiredTech
) {
}
