package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam("crm") String crm,
                           @RequestParam("password") String password,
                           HttpServletRequest request) {

        if (crm == null || crm.isEmpty() || password == null || password.isEmpty()) {
            return "error: emptyFields";
        }

        if (!crm.matches("\\d{6}")) {
            return "error: invalidCRM";
        }

        if (password.length() < 4) {
            return "error: shortPassword";
        }

        if (!userService.addUser(crm, password, "doctor")) {
            return "error: userExists";
        } else {
            return "success";
        }
    }
}
