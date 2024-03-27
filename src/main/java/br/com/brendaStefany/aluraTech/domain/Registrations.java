package br.com.brendaStefany.aluraTech.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "registrations")
@NoArgsConstructor
@AllArgsConstructor
public class Registrations {

    @EmbeddedId
    private RegisterId id;

    @CreationTimestamp
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;


    public Registrations(Users users, Courses courses) {
        this.setId(new RegisterId(
                users,courses
        ));
    }
}