package br.com.brendaStefany.aluraTech.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @NotBlank
    @Size(max = 20, message = "Username must have at most 20 characters.")
    @Pattern(regexp = "^[a-z]+$", message = "Username must contain only lowercase letters, non-numeric, and no spaces.")
    @Column(nullable = false, length = 20)
    private String username;

    @NotBlank(message = "Name user is required.")
    @Column(nullable = false)
    private String name;

    @Email(message = "Invalid email address.")
    @NotBlank(message = "Email is required.")
    @Column(unique = true,nullable = false)
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 3, max = 10, message = "Password must be between 3 and 10 characters.")
    @Column(nullable = false, length = 10)
    private String password;

    @NotBlank(message = "Role is required.")
    @Column(nullable = false)
    private String role;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creation_date;

    @OneToMany(mappedBy = "instructor_username", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("instructor_username")
    private List<Courses> courses;

    @OneToMany(mappedBy = "register_user", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("register_user")
    private List<Registrations> registration_user;

}