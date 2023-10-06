package dev.wantedpreonboardingbackend.recruitment.repository;

import dev.wantedpreonboardingbackend.company.domain.Company;
import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    Optional<Recruitment> findByIdAndCompany(Long id, Company company);
    List<Recruitment> findAll(Specification<Recruitment> word);
}
