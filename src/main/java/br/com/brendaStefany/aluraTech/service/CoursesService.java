package br.com.brendaStefany.aluraTech.service;

import br.com.brendaStefany.aluraTech.domain.Courses;
import br.com.brendaStefany.aluraTech.domain.CoursesStatus;
import br.com.brendaStefany.aluraTech.dto.courses.CoursesOutboundDTO;
import br.com.brendaStefany.aluraTech.dto.courses.CoursesOutboundListPageDTO;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import br.com.brendaStefany.aluraTech.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CoursesService {

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    UsersService usersService;

    public CoursesOutboundDTO addNewCourse(Courses course) {
        try {
            Optional<Courses> existingCourseByCode = coursesRepository.findByCode(course.getCode());
            if (existingCourseByCode.isPresent()) {
                throw new IllegalStateException("Course with this code already exists.");
            }

            UsersDTO instructor = usersService.findUserByUsernameDTO(course.getInstructor_username().getUsername());
            if (instructor == null) {
                throw new NoSuchElementException("Instructor not found with username: " + course.getInstructor_username().getUsername());
            }

            course.setStatus(CoursesStatus.ACTIVE);

            Courses savedCourse = coursesRepository.save(course);
            return new CoursesOutboundDTO(savedCourse,"Course created with successfully!!" );
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex);
        }
    }


    public void inactivateCourse(String code) {
        try {
            Courses course = coursesRepository.findByCode(code)
                    .orElseThrow(() -> new IllegalStateException("Course not found with code: " + code));

            if(course.getStatus() == CoursesStatus.INACTIVE)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The course is already inactive.");

            course.setStatus(CoursesStatus.INACTIVE);
            course.setInactive_at(LocalDateTime.now());
            coursesRepository.save(course);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    public Page<CoursesOutboundListPageDTO.CoursesDataOutboundList> findCourses(String status, Pageable paginacao) {
        Page<Courses> coursesPage;
        if (status != null && !status.isEmpty()) {
            CoursesStatus coursesStatus = CoursesStatus.valueOf(status.toUpperCase());
            coursesPage = coursesRepository.findByStatus(coursesStatus, paginacao);
        } else {
            coursesPage = coursesRepository.findAll(paginacao);
        }

        return coursesPage.map(course -> new CoursesOutboundListPageDTO.CoursesDataOutboundList(course));
    }

    public Courses findByCode(String code) {
        return coursesRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Course not found for code: " + code));
    }

}