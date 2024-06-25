package com.example.demo.service;

import com.example.demo.model.Prescription;
import com.example.demo.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getPrescriptionsByPharmacyCode(String pharmacyCode) {
        return prescriptionRepository.findByPharmacyCode(pharmacyCode);
    }

    public Prescription addPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }
}
