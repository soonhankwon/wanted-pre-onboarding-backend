package dev.wantedpreonboardingbackend.company.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class LocationInfo {

    private String nation;
    private String area;

    public LocationInfo(String nation, String area) {
        this.nation = nation;
        this.area = area;
    }
}
