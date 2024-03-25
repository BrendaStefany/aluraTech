package br.com.brendaStefany.aluraTech.dto.courses;

import br.com.brendaStefany.aluraTech.domain.Courses;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import br.com.brendaStefany.aluraTech.dto.users.UsersWithUsernameDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoursesOutboundListPageDTO {

    private List<CoursesDataOutboundList> dataList;


    public CoursesOutboundListPageDTO(Page<Courses> savedCourse) {
        this.dataList = savedCourse.getContent().stream()
                .map(CoursesDataOutboundList::new)
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class CoursesDataOutboundList {
        private String code;
        private String name;
        private UsersDTO instructor;
        private String description;
        private String status;

        public CoursesDataOutboundList(Courses course) {
            this.code = course.getCode();
            this.name = course.getName();
            this.description = course.getDescription();
            this.status = course.getStatusDescription(course.getStatus().name());
            this.instructor = new UsersDTO(course.getInstructor_username());
        }
    }

}
