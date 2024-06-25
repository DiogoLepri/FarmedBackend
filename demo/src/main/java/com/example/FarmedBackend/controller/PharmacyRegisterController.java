package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class PharmacyRegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/registerPh")
    public String register(@RequestParam("codigofarmacia") String codigofarmacia,
                           @RequestParam("password") String password,
                           HttpServletRequest request) {
        if (!userService.addUser(codigofarmacia, password, "pharmacist")) {
            return "error: userExists";
        } else {
            return "success";
        }
    }
}
