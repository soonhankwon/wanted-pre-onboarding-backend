package dev.wantedpreonboardingbackend.recruitment.domain;

import dev.wantedpreonboardingbackend.company.domain.Company;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentDetailGetResponse;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentGetResponse;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentUpdateRequest;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "recruitment", indexes = {
        @Index(name = "fk_company_idx", columnList = "company_id"),
        @Index(name = "idx_position_idx", columnList = "position")})
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

    private String requiredTech;

    public Recruitment(RecruitmentRegisterRequest dto, Company company) {
        this.position = dto.position();
        this.compensation = dto.compensation();
        this.description = dto.description();
        this.company = company;
        this.requiredTech = dto.tech();
    }

    public Long getId() {
        return this.id;
    }

    public Company getCompany() {
        return this.company;
    }

    public void update(RecruitmentUpdateRequest dto) {
        this.position = dto.position();
        this.compensation = dto.compensation();
        this.description = dto.description();
        this.requiredTech = dto.tech();
    }

    public RecruitmentGetResponse ofResponse() {
        return new RecruitmentGetResponse(
                this.id,
                this.company.getName(),
                this.company.getLocationInfo().getNation(),
                this.company.getLocationInfo().getArea(),
                this.position,
                this.compensation,
                this.requiredTech);
    }

    public RecruitmentDetailGetResponse ofDetailResponse(List<Long> ids) {
        return new RecruitmentDetailGetResponse(
                this.id,
                this.company.getName(),
                this.company.getLocationInfo().getNation(),
                this.company.getLocationInfo().getArea(),
                this.position,
                this.compensation,
                this.requiredTech,
                this.description,
                ids);
    }
}