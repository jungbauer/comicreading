package com.comicreading.repository;

import com.comicreading.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findImportedUsers() {
        User user = userRepository.findByEmail("test@test.com");

        assertEquals("test@test.com", user.getEmail());
        assertEquals("Test", user.getFirstName());
        assertEquals("Test", user.getLastName());
    }
}