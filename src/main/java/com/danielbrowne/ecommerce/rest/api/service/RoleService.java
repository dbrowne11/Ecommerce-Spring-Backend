package com.danielbrowne.ecommerce.rest.api.service;

import com.danielbrowne.ecommerce.rest.api.entity.Role;

import java.util.List;

public interface RoleService {
    public Role createRole(Role role);
    public List<Role> getAllRoles();
    public Role getRoleById(Long id);

    public Role updateRole(Long id, Role role);

    public String deleteRole(Long id);

}
