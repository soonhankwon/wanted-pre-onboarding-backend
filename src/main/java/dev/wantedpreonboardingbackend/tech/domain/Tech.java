package dev.wantedpreonboardingbackend.tech.domain;

import dev.wantedpreonboardingbackend.recruitment_notice.domain.RecruitmentNotice;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Tech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "recruitment_notice_id")
    private RecruitmentNotice recruitmentNotice;
}
