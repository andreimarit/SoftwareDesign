package com.lab4.demo.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultationDTO {


    private Integer id;
    private Date date;
    private String details;
    private Long patientId;
    private Long doctorId;
}
