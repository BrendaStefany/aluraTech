package br.com.brendaStefany.aluraTech.service;

import br.com.brendaStefany.aluraTech.domain.*;
import br.com.brendaStefany.aluraTech.dto.feedbacks.FeedbacksInboundDTO;
import br.com.brendaStefany.aluraTech.dto.feedbacks.FeedbacksOutboundDTO;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import br.com.brendaStefany.aluraTech.repository.FeedbacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class FeedbacksService {

    @Autowired
    RegistrationsService registrationsService;

    @Autowired
    EmailService emailService;

    @Autowired
    UsersService usersService;

    @Autowired
    FeedbacksRepository feedbacksRepository;

    @Autowired
    CoursesService coursesService;

    public FeedbacksOutboundDTO addNewFeedback(@Validated FeedbacksInboundDTO feedback) {
        try {

            UsersDTO users = usersService.findUserByUsernameDTO(feedback.getUsers().getUsername());

            Optional<Registrations> existingRegisterByUserAndCourse = registrationsService.findById(feedback.getUsers(), feedback.getCourses());
            if(existingRegisterByUserAndCourse.isEmpty())
                throw new IllegalStateException("Not exists Registration for user '" + feedback.getUsers().getUsername() +
                        "' and course '" + feedback.getCourses().getCode() + "'");

            Courses courses = coursesService.findByCode(feedback.getCourses().getCode());
            if(courses.getStatus() == CoursesStatus.INACTIVE)
                throw new IllegalStateException("Cannot register for an inactive course.");


            Optional<Feedbacks> existingFeedbackByUserAndCourse = feedbacksRepository.findById(new FeedbacksId(feedback.getUsers(), feedback.getCourses()));
            if (existingFeedbackByUserAndCourse.isPresent()) {
                Feedbacks updateFeedback = existingFeedbackByUserAndCourse.get();
                updateFeedback.setNote(feedback.getNote());
                updateFeedback.setMotive(feedback.getMotive());
                feedbacksRepository.save(updateFeedback);

                if(feedback.getNote() < 6.0) {
                    notifyInstructor(users.getEmail(), feedback);
                }

                return new FeedbacksOutboundDTO("Successfully registered!", updateFeedback);

            } else {
                Feedbacks newFeedback = new Feedbacks(new FeedbacksId(feedback.getUsers(), feedback.getCourses()),
                        feedback.getNote(), feedback.getMotive());
                feedbacksRepository.save(newFeedback);

                if(feedback.getNote() < 6.0) {
                    notifyInstructor(users.getEmail(),feedback);
                }

                return new FeedbacksOutboundDTO("Successfully registered!", newFeedback);
            }

        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    private void notifyInstructor(String emailInstructor, FeedbacksInboundDTO feedbacks) {
        String subject = "Unsatisfactory rating - Course : %s".formatted(feedbacks.getCourses().getCode());
        String body = "A student evaluated your course with a grade lower than 6.\nMotive: %s".formatted(feedbacks.getMotive());
        emailService.send(emailInstructor, subject, body);
    }

    public List<Object[]> findFeedbacksForNPS(){
        return feedbacksRepository.findFeedbacksForNPS();
    }

}
