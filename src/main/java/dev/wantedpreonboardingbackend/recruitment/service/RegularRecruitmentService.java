package dev.wantedpreonboardingbackend.recruitment.service;

import dev.wantedpreonboardingbackend.company.domain.Company;
import dev.wantedpreonboardingbackend.company.repository.CompanyRepository;
import dev.wantedpreonboardingbackend.exception.ApiException;
import dev.wantedpreonboardingbackend.exception.CustomErrorCode;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import dev.wantedpreonboardingbackend.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegularRecruitmentService implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void registerRecruitment(Long companyId, RecruitmentRegisterRequest dto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ApiException(CustomErrorCode.COMPANY_NOT_FOUND_INVALID_ID));
        Recruitment recruitment = new Recruitment(dto, company);
        recruitmentRepository.save(recruitment);
    }
}
