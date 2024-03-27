package br.com.brendaStefany.aluraTech.service;

import br.com.brendaStefany.aluraTech.domain.*;
import br.com.brendaStefany.aluraTech.dto.registrations.RegistrationsInboundDTO;
import br.com.brendaStefany.aluraTech.dto.registrations.RegistrationsOutboundDTO;
import br.com.brendaStefany.aluraTech.repository.RegistrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RegistrationsService {

    @Autowired
    RegistrationsRepository registrationsRepository;

    @Autowired
    CoursesService coursesService;

    public RegistrationsOutboundDTO addNewRegister(@Validated RegistrationsInboundDTO register) {
        try {
            Optional<Registrations> existingRegisterByUserAndCourse = registrationsRepository.findById(new RegisterId(register.getUsers(), register.getCourses()));
            if (existingRegisterByUserAndCourse.isPresent()) {
                throw new IllegalStateException("Registration already exists for user '" + register.getUsers().getUsername() +
                        "' and course '" + register.getCourses().getCode() + "'");
            }

            Courses courses = coursesService.findByCode(register.getCourses().getCode());
            if(courses.getStatus() == CoursesStatus.INACTIVE)
                throw new IllegalStateException("Cannot register for an inactive course.");


            Registrations newRegistration = new Registrations(register.getUsers(), register.getCourses());
            newRegistration = registrationsRepository.save(newRegistration);

            return new RegistrationsOutboundDTO("Successfully registered!", newRegistration);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    public Optional<Registrations> findById(Users users, Courses courses) {
        return registrationsRepository.findById(new RegisterId(users, courses));
    }
}
