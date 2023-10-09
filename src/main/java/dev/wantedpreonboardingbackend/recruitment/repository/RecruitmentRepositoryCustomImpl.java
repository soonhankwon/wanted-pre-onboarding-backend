package dev.wantedpreonboardingbackend.recruitment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.wantedpreonboardingbackend.company.domain.QCompany;
import dev.wantedpreonboardingbackend.recruitment.domain.QRecruitment;
import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class RecruitmentRepositoryCustomImpl implements RecruitmentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    QRecruitment recruitment = QRecruitment.recruitment;
    QCompany company = QCompany.company;

    @Override
    public List<Recruitment> findRecruitmentsByKeyword(String keyword) {
        return queryFactory.select(recruitment)
                .from(recruitment)
                .join(company).on(recruitment.company.eq(company))
                .where(recruitment.requiredTech.contains(keyword)
                        .or(recruitment.position.contains(keyword))
                        .or(company.name.contains(keyword)))
                .fetch();
    }

    @Override
    public List<Long> findRelatedRecruitmentsIdsByCompany(Recruitment recruitmentNotice) {
        return queryFactory.select(recruitment.id)
                .from(recruitment)
                .where(recruitment.company.eq(recruitmentNotice.getCompany())
                                .and(recruitment.id.ne(recruitmentNotice.getId())))
                .fetch();
    }
}
