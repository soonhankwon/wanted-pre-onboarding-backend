package dev.wantedpreonboardingbackend.recruitment.domain;

import dev.wantedpreonboardingbackend.company.domain.Company;
import dev.wantedpreonboardingbackend.company.domain.LocationInfo;
import dev.wantedpreonboardingbackend.exception.ApiException;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentDetailGetResponse;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentGetResponse;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentUpdateRequest;
import dev.wantedpreonboardingbackend.user.domain.User;
import dev.wantedpreonboardingbackend.user_recruitment.domain.Status;
import dev.wantedpreonboardingbackend.user_recruitment.domain.UserRecruitment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RecruitmentTest {

    @Test
    @DisplayName("채용공고 NoArgs 생성자 테스트")
    void noArgsConstructor() {
        assertThat(new Recruitment()).isNotNull();
    }

    @Test
    @DisplayName("채용공고 생성자 테스트")
    void constructor() {
        RecruitmentRegisterRequest dto = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment = new Recruitment(dto, company);
        assertThat(recruitment).isNotNull();
    }

    @Test
    @DisplayName("get id 테스트 - id는 DB 기본생성 전략을 따르므로 null")
    void getId() {
        RecruitmentRegisterRequest dto = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment = new Recruitment(dto, company);
        assertThat(recruitment.getId()).isNull();
    }

    @Test
    @DisplayName("get company 테스트")
    void getCompany() {
        RecruitmentRegisterRequest dto = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment = new Recruitment(dto, company);
        assertThat(recruitment.getCompany()).isEqualTo(company);
    }

    @Test
    @DisplayName("update 테스트")
    void update() {
        RecruitmentRegisterRequest dto1 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment = new Recruitment(dto1, company);

        RecruitmentUpdateRequest updateDto = new RecruitmentUpdateRequest("백엔드 주니어 개발자",
                1_500_000L,
                "원티드랩에서 백엔드 주니어 개발자를 '적극'채용합니다. 자격요건은..",
                "Python");

        recruitment.update(updateDto);

        RecruitmentRegisterRequest dto2 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_500_000L,
                "원티드랩에서 백엔드 주니어 개발자를 '적극'채용합니다. 자격요건은..",
                "Python");
        assertThat(recruitment).isEqualTo(new Recruitment(dto2, company));
    }

    @Test
    @DisplayName("채용공고 조회 응답 DTO 변환 테스트")
    void ofResponse() {
        RecruitmentRegisterRequest dto1 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment = new Recruitment(dto1, company);

        RecruitmentGetResponse recruitmentGetResponse = recruitment.ofResponse();

        assertThat(recruitmentGetResponse.recruitmentId()).isEqualTo(recruitment.getId());
        assertThat(recruitmentGetResponse.companyName()).isEqualTo(recruitment.getCompany().getName());
    }

    @Test
    @DisplayName("채용공고 상세페이지 조회 응답 DTO 변환 테스트")
    void ofDetailResponse() {
        RecruitmentRegisterRequest dto1 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment = new Recruitment(dto1, company);

        RecruitmentDetailGetResponse recruitmentDetailGetResponse = recruitment.ofDetailResponse(List.of(3L, 4L, 5L));

        assertThat(recruitmentDetailGetResponse.recruitmentId()).isEqualTo(recruitment.getId());
        assertThat(recruitmentDetailGetResponse.relatedRecruitmentsIdsByCompany()).isEqualTo(List.of(3L, 4L, 5L));
    }

    @Test
    @DisplayName("get UserRecruitments 테스트")
    void getUserRecruitments() {
        RecruitmentRegisterRequest dto1 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment = new Recruitment(dto1, company);

        assertThat(recruitment.getUserRecruitments().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("사용자 채용공고 지원 테스트")
    void apply() {
        User user = new User("abc@naver.com");

        RecruitmentRegisterRequest dto1 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment = new Recruitment(dto1, company);

        UserRecruitment userRecruitment = new UserRecruitment(user, recruitment, Status.COMPLETED_APPLYING);

        recruitment.apply(userRecruitment);

        assertThat(recruitment.getUserRecruitments().contains(userRecruitment)).isTrue();
    }

    @Test
    @DisplayName("사용자 채용공고 지원 예외처리 테스트 - 이미 지원했을 경우")
    void apply_already_applying() {
        User user = new User("abc@naver.com");

        RecruitmentRegisterRequest dto1 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment = new Recruitment(dto1, company);

        UserRecruitment userRecruitment = new UserRecruitment(user, recruitment, Status.COMPLETED_APPLYING);
        recruitment.getUserRecruitments().add(userRecruitment);

        assertThatThrownBy(() -> recruitment.apply(userRecruitment))
                .isInstanceOf(ApiException.class);
    }
}