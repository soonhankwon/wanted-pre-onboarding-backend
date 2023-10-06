package dev.wantedpreonboardingbackend.recruitment.service;

import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentUpdateRequest;

public interface RecruitmentService {

    void registerRecruitment(Long companyId, RecruitmentRegisterRequest dto);
    void updateRecruitment(Long recruitmentId, Long companyId, RecruitmentUpdateRequest dto);

    void deleteRecruitment(Long recruitmentId, Long companyId);
}
