package dev.wantedpreonboardingbackend.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Test
    @DisplayName("User NoArgs 테스트")
    void createUser_noArgs() {
        User user = new User();
        assertThat(user).isNotNull();
    }

    @Test
    @DisplayName("User 생성자 테스트 & EqualsAndHashCode")
    void createUser_constructor_equalsAndHashCode() {
        User user = new User("abc@naver.com");
        assertThat(user).isEqualTo(new User("abc@naver.com"));
    }

    @Test
    @DisplayName("User 생성시 유효하지 않은 이메일 검증")
    void validateEmail_constructor() {
        assertThatThrownBy(() -> new User("abcnaver.com"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}