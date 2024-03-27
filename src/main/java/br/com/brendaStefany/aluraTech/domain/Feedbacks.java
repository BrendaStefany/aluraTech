package br.com.brendaStefany.aluraTech.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedbacks {

    @EmbeddedId
    private FeedbacksId id;

    @Min(value = 0, message = "Note must be a non-negative number.")
    private double note;

    @NotBlank(message = "Motive is required.")
    private String motive;

}
