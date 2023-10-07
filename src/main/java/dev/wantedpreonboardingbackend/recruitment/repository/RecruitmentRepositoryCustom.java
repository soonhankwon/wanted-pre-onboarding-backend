package dev.wantedpreonboardingbackend.recruitment.repository;

import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;

import java.util.List;

public interface RecruitmentRepositoryCustom {
    List<Recruitment> findRecruitmentsByKeyword(String keyword);
    List<Long> findRelatedRecruitmentsIdsByCompany(Recruitment recruitment);
}
