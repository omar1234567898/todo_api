package com.example.do_api.service;

import com.example.do_api.model.User;
import com.example.do_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create or Update user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get a single user by ID
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    // Get all users in a company
    public List<User> getUsersByCompany(Long companyId) {
        return userRepository.findByCompanyId(companyId);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
