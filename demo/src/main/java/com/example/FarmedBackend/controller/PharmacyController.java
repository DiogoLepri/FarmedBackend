package com.example.demo.controller;

import com.example.demo.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PharmacyController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/pharmacy")
    public List<String> pharmacy(HttpServletRequest request, HttpSession session) {
        String user = (session != null) ? (String) session.getAttribute("user") : null;
        String pharmacyCode = (session != null) ? (String) session.getAttribute("pharmacyCode") : null;

        if (user == null) {
            throw new RuntimeException("User not authenticated");
        } else {
            return prescriptionService.getPrescriptionsByPharmacyCode(pharmacyCode);
        }
    }
}
