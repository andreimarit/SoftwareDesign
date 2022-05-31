package com.lab4.demo.repo;

import com.lab4.demo.model.Consultation;
import com.lab4.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    Consultation findByDoctorAndDate(User doctor, Date date);
}
