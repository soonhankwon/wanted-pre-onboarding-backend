package dev.wantedpreonboardingbackend.recruitment.service;

import dev.wantedpreonboardingbackend.company.domain.Company;
import dev.wantedpreonboardingbackend.company.repository.CompanyRepository;
import dev.wantedpreonboardingbackend.exception.ApiException;
import dev.wantedpreonboardingbackend.exception.CustomErrorCode;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.*;
import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import dev.wantedpreonboardingbackend.recruitment.repository.RecruitmentRepository;
import dev.wantedpreonboardingbackend.user.domain.User;
import dev.wantedpreonboardingbackend.user.repository.UserRepository;
import dev.wantedpreonboardingbackend.user_recruitment.domain.Status;
import dev.wantedpreonboardingbackend.user_recruitment.domain.UserRecruitment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegularRecruitmentService implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void registerRecruitment(Long companyId, RecruitmentRegisterRequest dto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ApiException(CustomErrorCode.COMPANY_NOT_FOUND_INVALID_ID));
        Recruitment recruitment = new Recruitment(dto, company);
        recruitmentRepository.save(recruitment);
    }

    @Override
    @Transactional
    public void updateRecruitment(Long recruitmentId, Long companyId, RecruitmentUpdateRequest dto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ApiException(CustomErrorCode.COMPANY_NOT_FOUND_INVALID_ID));
        Recruitment recruitment = recruitmentRepository.findByIdAndCompany(recruitmentId, company)
                .orElseThrow(() -> new ApiException(CustomErrorCode.RECRUITMENT_NOT_MATCHES_COMPANY_ID));

        recruitment.update(dto);
    }

    @Override
    @Transactional
    public void deleteRecruitment(Long recruitmentId, Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ApiException(CustomErrorCode.COMPANY_NOT_FOUND_INVALID_ID));
        Recruitment recruitment = recruitmentRepository.findByIdAndCompany(recruitmentId, company)
                .orElseThrow(() -> new ApiException(CustomErrorCode.RECRUITMENT_NOT_MATCHES_COMPANY_ID));
        recruitmentRepository.delete(recruitment);
    }

    @Override
    public List<RecruitmentGetResponse> getAllRecruitments(Pageable pageable) {
        Page<Recruitment> recruitmentPage = recruitmentRepository.findAll(pageable);
        return recruitmentPage.stream()
                .map(Recruitment::ofResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentGetResponse> searchRecruitments(String keyword) {
        return recruitmentRepository.findRecruitmentsByKeyword(keyword)
                .stream()
                .map(Recruitment::ofResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RecruitmentDetailGetResponse getRecruitmentDetail(Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new ApiException(CustomErrorCode.RECRUITMENT_NOT_FOUND_INVALID_ID));

        List<Long> ids = recruitmentRepository.findRelatedRecruitmentsIdsByCompany(recruitment);
        return recruitment.ofDetailResponse(ids);
    }

    @Override
    @Transactional
    public void applyRecruitment(Long recruitmentId, RecruitmentApplyRequest dto) {
        User user = userRepository.findUserByEmail(dto.email())
                .orElseThrow(() -> new ApiException(CustomErrorCode.USER_NOT_FOUND_INVALID_EMAIL));

        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new ApiException(CustomErrorCode.RECRUITMENT_NOT_FOUND_INVALID_ID));

        UserRecruitment userRecruitment = new UserRecruitment(user, recruitment, Status.COMPLETED_APPLYING);
        recruitment.apply(userRecruitment);
    }
}
