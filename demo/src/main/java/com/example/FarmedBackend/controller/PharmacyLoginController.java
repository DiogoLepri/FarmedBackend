package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class PharmacyLoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/loginPh")
    public String login(@RequestParam("codigofarmacia") String codigofarmacia,
                        @RequestParam("password") String password,
                        HttpSession session) {
        System.out.println("Tentando logar com codigofarmacia: " + codigofarmacia);

        if (userService.authenticateUser(codigofarmacia, password)) {
            session.setAttribute("user", codigofarmacia);
            System.out.println("Login bem sucedido para o codigofarmacia: " + codigofarmacia);
            return "success";
        } else {
            System.out.println("Login falhou: " + codigofarmacia);
            return "error: invalidCredentials";
        }
    }
}
