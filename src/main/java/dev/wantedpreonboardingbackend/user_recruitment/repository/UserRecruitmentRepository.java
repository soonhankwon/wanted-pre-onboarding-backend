package dev.wantedpreonboardingbackend.user_recruitment.repository;

import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import dev.wantedpreonboardingbackend.user.domain.User;
import dev.wantedpreonboardingbackend.user_recruitment.domain.Status;
import dev.wantedpreonboardingbackend.user_recruitment.domain.UserRecruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecruitmentRepository extends JpaRepository<UserRecruitment, Long> {
    boolean existsByUserAndRecruitmentAndStatusEquals(User user, Recruitment recruitment, Status status);
}
