package com.danielbrowne.ecommerce.rest.api.service.impl;

import com.danielbrowne.ecommerce.rest.api.entity.Role;
import com.danielbrowne.ecommerce.rest.api.repository.RoleRepository;
import com.danielbrowne.ecommerce.rest.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Role updateRole(Long id, Role role) {
        role.setId(id);
        return roleRepository.save(role);
    }

    @Override
    public String deleteRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return "Deleted Successfully";
        }
        return "Failed to delete, entity does not exist";
    }
}
