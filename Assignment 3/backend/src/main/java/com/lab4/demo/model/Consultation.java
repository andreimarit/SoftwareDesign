package com.lab4.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(length = 100)
    private String details;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User doctor;

}
