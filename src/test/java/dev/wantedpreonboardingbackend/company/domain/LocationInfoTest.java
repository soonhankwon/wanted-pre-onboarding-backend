package dev.wantedpreonboardingbackend.company.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationInfoTest {

    @Test
    @DisplayName("LocationInfo Constructor 테스트")
    void createLocationInfo_constructor_equalsAndHashcode() {
        LocationInfo locationInfo = new LocationInfo("대한민국", "서울");
        assertThat(locationInfo).isNotNull();
    }

    @Test
    @DisplayName("LocationInfo getNation 테스트")
    void getNation() {
        LocationInfo locationInfo = new LocationInfo("대한민국", "서울");
        assertThat(locationInfo.getNation()).isEqualTo("대한민국");
    }

    @Test
    @DisplayName("LocationInfo getArea 테스트")
    void getArea() {
        LocationInfo locationInfo = new LocationInfo("대한민국", "서울");
        assertThat(locationInfo.getArea()).isEqualTo("서울");
    }
}