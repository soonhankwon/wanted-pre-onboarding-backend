package dev.wantedpreonboardingbackend.recruitment.service;

import dev.wantedpreonboardingbackend.company.domain.Company;
import dev.wantedpreonboardingbackend.company.repository.CompanyRepository;
import dev.wantedpreonboardingbackend.exception.ApiException;
import dev.wantedpreonboardingbackend.exception.CustomErrorCode;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentUpdateRequest;
import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import dev.wantedpreonboardingbackend.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegularRecruitmentService implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

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
}
