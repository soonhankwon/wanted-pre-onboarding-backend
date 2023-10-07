package dev.wantedpreonboardingbackend.recruitment.domain;

import dev.wantedpreonboardingbackend.company.domain.Company;
import dev.wantedpreonboardingbackend.exception.ApiException;
import dev.wantedpreonboardingbackend.exception.CustomErrorCode;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentDetailGetResponse;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentGetResponse;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentUpdateRequest;
import dev.wantedpreonboardingbackend.user_recruitment.domain.UserRecruitment;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<UserRecruitment> userRecruitments = new ArrayList<>();

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

    public void apply(UserRecruitment userRecruitment) {
        if(existsByUserRecruitment(userRecruitment)) {
            throw new ApiException(CustomErrorCode.ALREADY_EXISTS_APPLYING);
        }
        userRecruitments.add(userRecruitment);
    }

    private boolean existsByUserRecruitment(UserRecruitment userRecruitment) {
        return this.userRecruitments.contains(userRecruitment);
    }
}