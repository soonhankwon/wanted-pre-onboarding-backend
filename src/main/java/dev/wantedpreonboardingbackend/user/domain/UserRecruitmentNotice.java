package dev.wantedpreonboardingbackend.user.domain;

import dev.wantedpreonboardingbackend.recruitment_notice.domain.RecruitmentNotice;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class UserRecruitmentNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recruitment_notice_id")
    private RecruitmentNotice recruitmentNotice;

    private String status;
}
