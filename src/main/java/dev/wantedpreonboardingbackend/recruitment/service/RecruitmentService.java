package dev.wantedpreonboardingbackend.recruitment.service;

import dev.wantedpreonboardingbackend.recruitment.controller.dto.*;

import java.util.List;

public interface RecruitmentService {

    void registerRecruitment(Long companyId, RecruitmentRegisterRequest dto);

    void updateRecruitment(Long recruitmentId, Long companyId, RecruitmentUpdateRequest dto);

    void deleteRecruitment(Long recruitmentId, Long companyId);

    List<RecruitmentGetResponse> getAllRecruitments();

    List<RecruitmentGetResponse> searchRecruitments(String keyword);

    RecruitmentDetailGetResponse getRecruitmentDetail(Long recruitmentId);

    void applyRecruitment(Long recruitmentId, RecruitmentApplyRequest dto);
}
