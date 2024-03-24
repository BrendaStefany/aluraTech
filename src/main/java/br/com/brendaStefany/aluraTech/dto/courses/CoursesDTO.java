package br.com.brendaStefany.aluraTech.dto.courses;

import br.com.brendaStefany.aluraTech.domain.Courses;
import br.com.brendaStefany.aluraTech.domain.CoursesStatus;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import br.com.brendaStefany.aluraTech.dto.users.UsersWithUsernameDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoursesDTO {

    private String message;
    private CoursesData data;
    private List<CoursesData> dataList;


    public CoursesDTO(String message, Courses savedCourse) {
        this.message = message;
        this.data = new CoursesData();
        this.data.setCode(savedCourse.getCode());
        this.data.setName(savedCourse.getName());
        this.data.setInstructor(new UsersDTO(savedCourse.getInstructor_username()));
        this.data.setDescription(savedCourse.getDescription());
        this.data.setStatus(getStatusDescription(savedCourse.getStatus().name()));
    }

    public CoursesDTO(Page<Courses> savedCourse) {
        this.dataList = new ArrayList<>();

        List<Courses> coursesList = savedCourse.getContent();
        for (Courses course : coursesList) {
            CoursesData courseData = new CoursesData();
            courseData.setCode(course.getCode());
            courseData.setName(course.getName());
            courseData.setDescription(course.getDescription());
            courseData.setStatus(getStatusDescription(course.getStatus().name()));

            UsersWithUsernameDTO instructorDTO = new UsersWithUsernameDTO(
                    course.getInstructor_username()
            );
            courseData.setInstructor(instructorDTO);

            this.dataList.add(courseData);
        }
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

    private String getStatusDescription(String status) {
        CoursesStatus coursesStatus = CoursesStatus.valueOf(status.toUpperCase());
        return coursesStatus == CoursesStatus.ACTIVE ? "Ativo" : "Inativo";
    }
}
