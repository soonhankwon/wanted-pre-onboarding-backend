package dev.wantedpreonboardingbackend.user_recruitment.domain;

import dev.wantedpreonboardingbackend.company.domain.Company;
import dev.wantedpreonboardingbackend.company.domain.LocationInfo;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import dev.wantedpreonboardingbackend.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserRecruitmentTest {

    @Test
    @DisplayName("유저 채용공고 NoArgs 테스트")
    void noArgsConstructor() {
        assertThat(new UserRecruitment()).isNotNull();
    }

    @Test
    @DisplayName("유저 채용공고 생성자 테스트")
    void constructor() {
        User user = new User("abc@naver.com");

        RecruitmentRegisterRequest dto1 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company1 = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Company company2 = new Company("원티드코리아", new LocationInfo("한국", "부산"));
        Recruitment recruitment1 = new Recruitment(dto1, company1);
        Recruitment recruitment2 = new Recruitment(dto1, company2);

        UserRecruitment userRecruitment1 = new UserRecruitment(user, recruitment1, Status.COMPLETED_APPLYING);
        UserRecruitment userRecruitment2 = new UserRecruitment(user, recruitment2, Status.COMPLETED_APPLYING);
        assertThat(userRecruitment1).isNotEqualTo(userRecruitment2);
    }

    @Test
    @DisplayName("equals 테스트")
    void testEquals() {
        User user = new User("abc@naver.com");

        RecruitmentRegisterRequest dto1 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company1 = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Company company2 = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment1 = new Recruitment(dto1, company1);
        Recruitment recruitment2 = new Recruitment(dto1, company2);

        UserRecruitment userRecruitment1 = new UserRecruitment(user, recruitment1, Status.COMPLETED_APPLYING);
        UserRecruitment userRecruitment2 = new UserRecruitment(user, recruitment2, Status.COMPLETED_APPLYING);
        assertThat(userRecruitment1.equals(userRecruitment2)).isTrue();
    }

    @Test
    @DisplayName("hashcode 테스트")
    void testHashCode() {
        User user = new User("abc@naver.com");

        RecruitmentRegisterRequest dto1 = new RecruitmentRegisterRequest("백엔드 주니어 개발자",
                1_000_000L,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Python");
        Company company1 = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Company company2 = new Company("원티드랩", new LocationInfo("한국", "서울"));
        Recruitment recruitment1 = new Recruitment(dto1, company1);
        Recruitment recruitment2 = new Recruitment(dto1, company2);

        UserRecruitment userRecruitment1 = new UserRecruitment(user, recruitment1, Status.COMPLETED_APPLYING);
        UserRecruitment userRecruitment2 = new UserRecruitment(user, recruitment2, Status.COMPLETED_APPLYING);
        assertThat(userRecruitment1.hashCode()).isEqualTo(userRecruitment2.hashCode());
    }
}