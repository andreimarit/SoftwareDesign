package com.lab4.demo.patient;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.model.Patient;
import com.lab4.demo.dto.PatientDTO;
import com.lab4.demo.repo.PatientRepository;
import com.lab4.demo.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PatientServiceIntegrationTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<Patient> patients = TestCreationFactory.listOf(Patient.class);
        patientRepository.saveAll(patients);

        List<PatientDTO> all = patientService.findAll();

        Assertions.assertEquals(patients.size(), all.size());
    }
}