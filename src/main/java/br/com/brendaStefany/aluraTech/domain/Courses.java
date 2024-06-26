package br.com.brendaStefany.aluraTech.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Courses {

    @Id
    @NotBlank(message = "Code is required.")
    @Pattern(regexp = "^[a-zA-Z]+(?:-[a-zA-Z]+)*$", message = "Code must be textual, without spaces, numbers, or special characters, but may be separated by '-' (e.g., spring-boot-advanced).")
    @Column(nullable = false, length = 10)
    private String code;

    @NotBlank(message = "Name courses is required.")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "instructor_username", referencedColumnName = "username", nullable = false)
    @JsonIgnoreProperties("courses")
    private Users instructor_username;

    @NotBlank(message = "Description is required.")
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private CoursesStatus status;

    @CreationTimestamp
    private LocalDateTime created_at;

    private LocalDateTime inactive_at;

    @OneToMany(mappedBy = "id.course", cascade = CascadeType.REMOVE)
    private List<Registrations> registers;

    public String getStatusDescription(String status) {
        CoursesStatus coursesStatus = CoursesStatus.valueOf(status.toUpperCase());
        return coursesStatus == CoursesStatus.ACTIVE ? "Ativo" : "Inativo";
    }

}