package br.com.brendaStefany.aluraTech.dto.feedbacks;

import br.com.brendaStefany.aluraTech.domain.Courses;
import br.com.brendaStefany.aluraTech.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbacksInboundDTO {

    private Users users;
    private Courses courses;
    private double note;
    private String motive;

}
