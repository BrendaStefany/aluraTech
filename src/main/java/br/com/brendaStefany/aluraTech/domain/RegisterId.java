package br.com.brendaStefany.aluraTech.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class RegisterId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
    private Courses course;

    public RegisterId(Users user, Courses course) {
        this.course = course;
        this.user = user;
    }
}