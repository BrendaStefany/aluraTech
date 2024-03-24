package br.com.brendaStefany.aluraTech.controller;

import br.com.brendaStefany.aluraTech.domain.Registrations;
import br.com.brendaStefany.aluraTech.dto.registrations.RegistrationsDTO;
import br.com.brendaStefany.aluraTech.service.RegistrationsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrations")
public class RegistrationsController {

    @Autowired
    RegistrationsService registrationsService;

    @PostMapping()
    public ResponseEntity<RegistrationsDTO> addNewRegister(@RequestBody @Valid Registrations register){
        RegistrationsDTO registerDTO = registrationsService.addNewRegister(register);
        return new ResponseEntity<>(registerDTO, HttpStatus.CREATED);
    }


}
