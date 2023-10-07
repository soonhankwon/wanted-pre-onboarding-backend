package dev.wantedpreonboardingbackend.recruitment.controller.dto;

import java.util.List;

public record RecruitmentDetailGetResponse(
        Long recruitmentId,
        String companyName,
        String nation,
        String area,
        String position,
        Long compensation,
        String requiredTech,
        String description,
        List<Long> relatedRecruitmentsByCompany
) {
}
