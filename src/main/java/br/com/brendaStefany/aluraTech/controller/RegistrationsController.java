package br.com.brendaStefany.aluraTech.controller;

import br.com.brendaStefany.aluraTech.dto.registrations.RegistrationsInboundDTO;
import br.com.brendaStefany.aluraTech.dto.registrations.RegistrationsOutboundDTO;
import br.com.brendaStefany.aluraTech.service.RegistrationsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrations")
public class RegistrationsController {

    @Autowired
    RegistrationsService registrationsService;

    @PostMapping()
    public ResponseEntity<RegistrationsOutboundDTO> addNewRegister(@RequestBody @Valid RegistrationsInboundDTO register){
        RegistrationsOutboundDTO registerDTO = registrationsService.addNewRegister(register);
        return new ResponseEntity<>(registerDTO, HttpStatus.CREATED);
    }

}
