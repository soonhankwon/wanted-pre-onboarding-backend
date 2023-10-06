package dev.wantedpreonboardingbackend.recruitment.domain;

import dev.wantedpreonboardingbackend.company.domain.Company;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.tech.domain.Tech;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    private Long compensation;

    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL)
    private Set<Tech> requiredTechs = new HashSet<>();

    public Recruitment(RecruitmentRegisterRequest dto, Company company) {
        this.position = dto.position();
        this.compensation = dto.compensation();
        this.description = dto.description();
        this.company = company;
        this.requiredTechs.add(new Tech(dto.tech()));
    }
}
