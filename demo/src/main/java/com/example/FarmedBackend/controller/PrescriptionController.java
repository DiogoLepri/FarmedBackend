package com.example.demo.controller;

import com.example.demo.model.Prescription;
import com.example.demo.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/prescriptions")
    public String addPrescription(@RequestParam("name") String name,
                                  @RequestParam("description") String description,
                                  @RequestParam("cpf") String cpf,
                                  @RequestParam("borndate") String borndate,
                                  @RequestParam("healthinsurance") String healthinsurance,
                                  @RequestParam("doctorname") String doctorname,
                                  @RequestParam("CRM") String crm,
                                  @RequestParam("medicinename") String medicinename,
                                  @RequestParam("dosage") String dosage,
                                  @RequestParam("amount") String amount,
                                  @RequestParam("Admin") String admin,
                                  @RequestParam("obs") String obs,
                                  @RequestParam("pharmacyCode") String pharmacyCode,
                                  HttpServletRequest request) {

        if (name == null || name.isEmpty() ||
                description == null || description.isEmpty() ||
                cpf == null || cpf.isEmpty() ||
                borndate == null || borndate.isEmpty() ||
                healthinsurance == null || healthinsurance.isEmpty() ||
                doctorname == null || doctorname.isEmpty() ||
                crm == null || crm.isEmpty() ||
                medicinename == null || medicinename.isEmpty() ||
                dosage == null || dosage.isEmpty() ||
                amount == null || amount.isEmpty() ||
                admin == null || admin.isEmpty() ||
                obs == null || obs.isEmpty() ||
                pharmacyCode == null || pharmacyCode.isEmpty()) {
            return "error: emptyFields";
        }

        if (!cpf.matches("\\d{11}")) {
            return "error: invalidCPF";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(borndate);
        } catch (ParseException e) {
            return "error: invalidBorndate";
        }

        if (!crm.matches("\\d{4,6}")) {
            return "error: invalidCRM";
        }

        try {
            int amountValue = Integer.parseInt(amount);
            if (amountValue <= 0) {
                return "error: invalidAmount";
            }
        } catch (NumberFormatException e) {
            return "error: invalidAmount";
        }

        Prescription prescription = new Prescription();
        prescription.setName(name);
        prescription.setDescription(description);
        prescription.setCpf(cpf);
        prescription.setBorndate(borndate);
        prescription.setHealthinsurance(healthinsurance);
        prescription.setDoctorname(doctorname);
        prescription.setCrm(crm);
        prescription.setMedicinename(medicinename);
        prescription.setDosage(dosage);
        prescription.setAmount(amount);
        prescription.setAdmin(admin);
        prescription.setObs(obs);
        prescription.setPharmacyCode(pharmacyCode);

        prescriptionService.addPrescription(prescription);

        return "success";
    }

    @GetMapping("/prescriptions")
    public List<Prescription> getPrescriptions(@RequestParam("pharmacyCode") String pharmacyCode,
                                               HttpSession se
