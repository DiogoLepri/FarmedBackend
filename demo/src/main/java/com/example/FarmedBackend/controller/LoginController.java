package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("crm") String crm,
                        @RequestParam("password") String password,
                        HttpSession session) {
        if (crm == null || crm.isEmpty() || password == null || password.isEmpty()) {
            return "error: emptyFields";
        }

        if (!crm.matches("\\d{6}")) {
            return "error: invalidCRM";
        }

        if (userService.authenticateUser(crm, password)) {
            session.setAttribute("user", crm);
            System.out.println("Login successful for CRM: " + crm);
            return "success";
        } else {
            System.out.println("Login failed for CRM: " + crm);
            return "error: invalidCredentials";
        }
    }
}
