package br.com.brendaStefany.aluraTech.dto.registrations;

import br.com.brendaStefany.aluraTech.domain.Courses;
import br.com.brendaStefany.aluraTech.domain.Registrations;
import br.com.brendaStefany.aluraTech.domain.Users;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationsInboundDTO {

    private Users users;
    private Courses courses;

    public RegistrationsInboundDTO(Users user, Courses course) {
        this.courses = course;
        this.users = user;
    }




}
