package br.com.brendaStefany.aluraTech.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class FeedbacksId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
    private Courses course;

    public FeedbacksId(Users user, Courses course) {
        this.course = course;
        this.user = user;
    }
}
