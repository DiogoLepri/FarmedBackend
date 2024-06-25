package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean authenticateUser(String crm, String password) {
        User user = userRepository.findByCrm(crm);
        return user != null && user.getPassword().equals(password);
    }

    public boolean addUser(String crm, String password, String role) {
        if (userRepository.findByCrm(crm) != null) {
            return false;
        }
        User user = new User();
        user.setCrm(crm);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
        return true;
    }
}
