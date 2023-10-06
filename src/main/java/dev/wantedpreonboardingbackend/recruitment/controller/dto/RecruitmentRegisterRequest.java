package dev.wantedpreonboardingbackend.recruitment.controller.dto;

public record RecruitmentRegisterRequest(
        String position,
        Long compensation,
        String description,
        String tech
)  {
}
