package com.example.do_api.controller;

import com.example.do_api.model.User;
import com.example.do_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create or Update a User
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUser(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all users in a company
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<User>> getUsersByCompany(@PathVariable Long companyId, @RequestParam String role) {
        // Check if the user has the correct role
        if (role.equals("Super-User") || role.equals("Company-Admin")) {
            List<User> users = userService.getUsersByCompany(companyId);
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // Delete a User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
