package br.com.brendaStefany.aluraTech.dto.feedbacks;

import br.com.brendaStefany.aluraTech.domain.Feedbacks;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbacksOutboundDTO {

    private String message;
    private FeedbacksData data;

    public FeedbacksOutboundDTO(String message, Feedbacks savedFeedback) {
        this.message = message;
        this.data = new FeedbacksData();
        this.data.setUser(savedFeedback.getId().getUser().getUsername());
        this.data.setCourse(savedFeedback.getId().getCourse().getCode());
        this.data.setNote(savedFeedback.getNote());
        this.data.setMotive(savedFeedback.getMotive());
    }

    @Getter
    @Setter
    public class FeedbacksData {
        private String user;
        private String course;
        private double note;
        private String motive;
    }

}
