package dev.wantedpreonboardingbackend.recruitment_notice.domain;

import dev.wantedpreonboardingbackend.tech.domain.Tech;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
public class RecruitmentNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    private Long compensation;

    private String description;

    @OneToMany(mappedBy = "recruitmentNotice", cascade = CascadeType.ALL)
    private Set<Tech> requiredTechs = new HashSet<>();
}
