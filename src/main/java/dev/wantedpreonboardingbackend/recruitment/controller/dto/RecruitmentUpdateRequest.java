package dev.wantedpreonboardingbackend.recruitment.controller.dto;

public record RecruitmentUpdateRequest(
        String position,
        Long compensation,
        String description,
        String tech
        ) {
}
