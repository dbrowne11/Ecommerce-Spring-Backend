package com.danielbrowne.ecommerce.rest.api.controller;

import com.danielbrowne.ecommerce.rest.api.entity.User;
import com.danielbrowne.ecommerce.rest.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        List<User> data = userService.getAllUsers();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User data = userService.getUserById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user) {
        User data = userService.createUser(user);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User data = userService.updateUser(id, user);
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        try {
            String data = userService.deleteUser(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("Entry not found", HttpStatus.NOT_FOUND);
        }
    }
}
