package com.lab4.demo.repo;

import com.lab4.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

   // List<Patient> findByNameContainingAndAuthorContainingAndGenreContainingIgnoreCase(String title, String author, String genre);
}
