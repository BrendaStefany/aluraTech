package br.com.brendaStefany.aluraTech.dto.courses;

import br.com.brendaStefany.aluraTech.domain.Courses;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoursesOutboundDTO {

    private String message;
    private CoursesOutboundData data;

    public CoursesOutboundDTO(Courses savedCourse, String message) {
        this.message = message;
        this.data = new CoursesOutboundData(savedCourse);
    }

    @Getter
    @Setter
    public static class CoursesOutboundData {
        private String code;
        private String name;
        private UsersDTO instructor;
        private String description;
        private String status;

        public CoursesOutboundData(Courses course) {
            this.code = course.getCode();
            this.name = course.getName();
            this.description = course.getDescription();
            this.status = course.getStatusDescription(course.getStatus().name());
            this.instructor = new UsersDTO(course.getInstructor_username());
        }
    }
}
