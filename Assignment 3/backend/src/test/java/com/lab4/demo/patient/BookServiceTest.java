package com.lab4.demo.patient;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.model.Patient;
import com.lab4.demo.dto.PatientDTO;
import com.lab4.demo.repo.PatientRepository;
import com.lab4.demo.service.mapper.PatientMapper;
import com.lab4.demo.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void findAll() {
        List<Patient> patients = TestCreationFactory.listOf(Patient.class);
        when(patientRepository.findAll()).thenReturn(patients);

        List<PatientDTO> all = patientService.findAll();

        Assertions.assertEquals(patients.size(), all.size());
    }
}