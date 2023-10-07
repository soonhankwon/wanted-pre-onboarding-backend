package dev.wantedpreonboardingbackend.company.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
@Table(name = "company", indexes = {
        @Index(name = "idx_name_idx", columnList = "name")})
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private LocationInfo locationInfo;

    public Company(String name, LocationInfo locationInfo) {
        this.name = name;
        this.locationInfo = locationInfo;
    }
}
