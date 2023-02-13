package com.comicreading.repository;

import com.comicreading.domain.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findImportedRoles() {
        Role admin = roleRepository.findByName("ROLE_ADMIN");
        Role user = roleRepository.findByName("ROLE_USER");
        Role demo = roleRepository.findByName("ROLE_DEMO");

        assertEquals("ROLE_ADMIN", admin.getName());
        assertEquals("ROLE_DEMO", demo.getName());
        assertEquals("ROLE_USER", user.getName());

        assertEquals(3, roleRepository.findAll().size());
    }
}