package dev.wantedpreonboardingbackend.recruitment.repository;

import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}
