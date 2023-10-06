package dev.wantedpreonboardingbackend.company.repository;

import dev.wantedpreonboardingbackend.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
