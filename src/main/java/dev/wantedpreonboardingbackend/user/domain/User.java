package dev.wantedpreonboardingbackend.user.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "`user`")
public class User {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    public User(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("이메일 형식에 맞지 않습니다.");
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
