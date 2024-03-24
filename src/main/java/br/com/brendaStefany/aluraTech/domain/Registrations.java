package br.com.brendaStefany.aluraTech.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "registrations")
public class Registrations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "register_user", referencedColumnName = "username", nullable = false)
    @JsonIgnoreProperties("registration_user")
    private Users register_user;

    @ManyToOne
    @JoinColumn(name = "register_course", referencedColumnName = "code", nullable = false)
    @JsonIgnoreProperties("registration_course")
    private Courses register_course;

    private LocalDateTime registration_date;

}