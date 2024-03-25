package br.com.brendaStefany.aluraTech.dto.courses;

import br.com.brendaStefany.aluraTech.domain.Courses;
import br.com.brendaStefany.aluraTech.domain.CoursesStatus;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoursesDTO {

    private CoursesData data;

    public CoursesDTO(Courses savedCourse) {
        this.data = new CoursesData();

        this.data.setCode(savedCourse.getCode());
        this.data.setName(savedCourse.getName());
        this.data.setInstructor(new UsersDTO(savedCourse.getInstructor_username()));
        this.data.setDescription(savedCourse.getDescription());
        this.data.setStatus(savedCourse.getStatusDescription(savedCourse.getStatus().name()));
    }

    @Getter
    @Setter
    public class CoursesData {
        private String code;
        private String name;
        private UsersDTO instructor;
        private String description;
        private String status;



    }


}
