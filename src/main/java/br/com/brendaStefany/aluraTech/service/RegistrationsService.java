package br.com.brendaStefany.aluraTech.service;

import br.com.brendaStefany.aluraTech.domain.CoursesStatus;
import br.com.brendaStefany.aluraTech.domain.Registrations;
import br.com.brendaStefany.aluraTech.dto.registrations.RegistrationsDTO;
import br.com.brendaStefany.aluraTech.repository.RegistrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegistrationsService {

    @Autowired
    RegistrationsRepository registrationsRepository;

    @Autowired
    CoursesService coursesService;

    public RegistrationsDTO addNewRegister(@Validated  Registrations register) {
        try {
            Optional<CoursesStatus> status = coursesService.findStatusByCode(register.getRegister_course().getCode());
            if(status.get() == CoursesStatus.INACTIVE)
                throw new IllegalStateException("Inactive course, impossible to register.");

            register.setRegistration_date(LocalDateTime.now());

            Registrations savedUser = registrationsRepository.save(register);
            return new RegistrationsDTO("Successfully registration!",savedUser);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }

}
