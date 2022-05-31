package com.lab4.demo.controller;

import com.lab4.demo.dto.ConsultationDTO;
import com.lab4.demo.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.CONSULTATIONS;
import static com.lab4.demo.UrlMapping.ID;

@RequestMapping(CONSULTATIONS)
@RestController
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDTO> findAll(){
        return consultationService.findAll();
    }

    @PostMapping
    public ConsultationDTO create(@RequestBody ConsultationDTO consultationDTO){
        return consultationService.create(consultationDTO);
    }

    @PatchMapping(ID)
    public ConsultationDTO edit(@RequestBody ConsultationDTO consultationDTO, @PathVariable Long id){
        return consultationService.edit(id, consultationDTO);
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {
        consultationService.deleteById(id);
    }

}
