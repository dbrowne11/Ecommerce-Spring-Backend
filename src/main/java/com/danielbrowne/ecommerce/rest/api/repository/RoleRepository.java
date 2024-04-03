package com.danielbrowne.ecommerce.rest.api.repository;

import com.danielbrowne.ecommerce.rest.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String admin);
}
