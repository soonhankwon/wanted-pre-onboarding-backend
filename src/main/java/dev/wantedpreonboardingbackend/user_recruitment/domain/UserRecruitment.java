package dev.wantedpreonboardingbackend.user_recruitment.domain;

import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import dev.wantedpreonboardingbackend.user.domain.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecruitment that = (UserRecruitment) o;
        return Objects.equals(user, that.user) && Objects.equals(recruitment, that.recruitment) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, recruitment, status);
    }
}
