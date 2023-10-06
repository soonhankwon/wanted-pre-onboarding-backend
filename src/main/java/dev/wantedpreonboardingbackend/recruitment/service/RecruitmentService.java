package dev.wantedpreonboardingbackend.recruitment.service;

import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentGetResponse;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentUpdateRequest;

import java.util.List;

public interface RecruitmentService {

    void registerRecruitment(Long companyId, RecruitmentRegisterRequest dto);

    void updateRecruitment(Long recruitmentId, Long companyId, RecruitmentUpdateRequest dto);

    void deleteRecruitment(Long recruitmentId, Long companyId);

    List<RecruitmentGetResponse> getAllRecruitments();
}
