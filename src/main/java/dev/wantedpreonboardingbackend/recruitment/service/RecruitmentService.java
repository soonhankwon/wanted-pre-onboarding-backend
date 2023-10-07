package dev.wantedpreonboardingbackend.recruitment.service;

import dev.wantedpreonboardingbackend.recruitment.controller.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitmentService {

    void registerRecruitment(Long companyId, RecruitmentRegisterRequest dto);

    void updateRecruitment(Long recruitmentId, Long companyId, RecruitmentUpdateRequest dto);

    void deleteRecruitment(Long recruitmentId, Long companyId);

    List<RecruitmentGetResponse> getAllRecruitments(Pageable pageable);

    List<RecruitmentGetResponse> searchRecruitments(String keyword);

    RecruitmentDetailGetResponse getRecruitmentDetail(Long recruitmentId);

    void applyRecruitment(Long recruitmentId, RecruitmentApplyRequest dto);
}
