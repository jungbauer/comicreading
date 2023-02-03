package com.comicreading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comicreading.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Role findByName(String name);
}
