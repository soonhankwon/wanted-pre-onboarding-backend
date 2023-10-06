package dev.wantedpreonboardingbackend.tech.domain;

import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Tech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    public Tech(String name) {
        this.name = name;
    }
}
