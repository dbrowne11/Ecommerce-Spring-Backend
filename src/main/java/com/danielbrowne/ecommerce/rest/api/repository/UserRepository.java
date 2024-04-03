package com.danielbrowne.ecommerce.rest.api.repository;

import com.danielbrowne.ecommerce.rest.api.entity.User;
import com.danielbrowne.ecommerce.rest.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

}