package com.phsanzio.api_blog.domain.repositories;

import com.phsanzio.api_blog.domain.model.user.LoginRequestDTO;
import com.phsanzio.api_blog.domain.model.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("findByLogin should return a user when it exists")
    void findByLoginTest() {
        LoginRequestDTO data = new LoginRequestDTO("phsanzio", "pedro2306");
        User user = this.createUser(data);
        Optional<UserDetails> result = Optional.ofNullable(this.userRepository.findByLogin(user.getLogin()));
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getUsername()).isEqualTo(user.getLogin());
    }

    @Test
    @DisplayName("findByLogin should not return a user when it not exists")
    void findByLoginTest2() {
        Optional<UserDetails> result = Optional.ofNullable(this.userRepository.findByLogin("null"));
        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(LoginRequestDTO data) {
        User user = new User(data);
        entityManager.persist(user);
        return user;
    }

}