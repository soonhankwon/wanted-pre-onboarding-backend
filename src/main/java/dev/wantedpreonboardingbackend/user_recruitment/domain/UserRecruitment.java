package dev.wantedpreonboardingbackend.user_recruitment.domain;

import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import dev.wantedpreonboardingbackend.user.domain.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "user_recruitment", indexes = {
        @Index(name = "fk_user_idx", columnList = "user_id"),
        @Index(name = "fk_recruitment_idx", columnList = "recruitment_id"),
        @Index(name = "idx_status_idx", columnList = "status")
})
public class UserRecruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @Enumerated(EnumType.STRING)
    private Status status;

    public UserRecruitment(User user, Recruitment recruitment, Status status) {
        this.user = user;
        this.recruitment = recruitment;
        this.status = status;
    }
}
