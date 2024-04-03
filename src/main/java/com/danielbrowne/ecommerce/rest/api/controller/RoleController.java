package com.danielbrowne.ecommerce.rest.api.controller;

import com.danielbrowne.ecommerce.rest.api.entity.Role;
import com.danielbrowne.ecommerce.rest.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping
    ResponseEntity<List<Role>> getAllRoles() {
        List<Role> data = roleService.getAllRoles();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
        Role data = roleService.getRoleById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role data = roleService.createRole(role);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Role> updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
        Role data = roleService.updateRole(id, role);
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
        try {
            String data = roleService.deleteRole(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("Entry not found", HttpStatus.NOT_FOUND);
        }
    }
}
