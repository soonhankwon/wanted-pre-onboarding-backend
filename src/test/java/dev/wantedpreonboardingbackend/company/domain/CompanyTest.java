package dev.wantedpreonboardingbackend.company.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyTest {

    @Test
    @DisplayName("Company NoArgs 테스트")
    void createCompany_noArgs() {
        Company company = new Company();
        assertThat(company).isNotNull();
    }

    @Test
    @DisplayName("Company Constructor 테스트")
    void createCompany_constructor_equalsAndHashCode() {
        Company company = new Company("원티드랩", new LocationInfo("대한민국", "서울"));
        assertThat(company).isEqualTo(new Company("원티드랩", new LocationInfo("대한민국", "서울")));
    }

    @Test
    @DisplayName("Company getId 테스트")
    void getId() {
        Company company = new Company("원티드랩", new LocationInfo("대한민국", "서울"));
        assertThat(company.getId()).isNull();
    }

    @Test
    @DisplayName("Company getName 테스트")
    void getName() {
        Company company = new Company("원티드랩", new LocationInfo("대한민국", "서울"));
        assertThat(company.getName()).isEqualTo("원티드랩");
    }

    @Test
    @DisplayName("Company getLocationInfo 테스트")
    void getLocationInfo() {
        Company company = new Company("원티드랩", new LocationInfo("대한민국", "서울"));
        assertThat(company.getLocationInfo()).isEqualTo(new LocationInfo("대한민국", "서울"));
    }
}