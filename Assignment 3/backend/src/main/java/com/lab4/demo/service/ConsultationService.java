package com.lab4.demo.service;

import com.lab4.demo.dto.ConsultationDTO;
import com.lab4.demo.model.Consultation;
import com.lab4.demo.model.Patient;
import com.lab4.demo.model.User;
import com.lab4.demo.repo.ConsultationRepository;
import com.lab4.demo.repo.PatientRepository;
import com.lab4.demo.repo.UserRepository;
import com.lab4.demo.service.mapper.ConsultationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public List<ConsultationDTO> findAll(){
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    public Consultation findById(Long id){
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found: " + id));
    }

    public void deleteById(Long id){
        consultationRepository.deleteById(id);
    }

    public ConsultationDTO create(ConsultationDTO consultationDTO){
        User doctor = userRepository.findById( consultationDTO.getDoctorId()).orElseThrow(() -> new EntityNotFoundException("Doctor NOT FOUND!!"));
        Patient patient = patientRepository.findById(consultationDTO.getPatientId()).orElseThrow(() -> new EntityNotFoundException("Patient NOT FOUND!!!"));

        Consultation newConsultation = consultationRepository.findByDoctorAndDate(doctor, consultationDTO.getDate());

        if(newConsultation == null)
        {
            Consultation consultation = Consultation.builder()
                    .doctor(doctor)
                    .patient(patient)
                    .date(consultationDTO.getDate())
                    .details(consultationDTO.getDetails())
                    .build();

            return consultationMapper.toDto(consultationRepository.save(consultation));
        }else
        {
            throw new RuntimeException("DOCTOR NOT AVAILABLE!");
        }
    }

    public ConsultationDTO edit(Long id, ConsultationDTO consultationDTO){
        Consultation consultation = consultationMapper.fromDto(consultationDTO);
        consultation.setId(id);

        return consultationMapper.toDto(consultationRepository.save(consultation));
    }
}