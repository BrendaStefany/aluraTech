package br.com.brendaStefany.aluraTech.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users implements UserDetails {

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
    @Column(nullable = false, length = 255)
    private String password;

    @NotBlank(message = "Role is required.")
    @Column(nullable = false)
    private String role;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creation_date;

    @OneToMany(mappedBy = "instructor_username", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("instructor_username")
    private List<Courses> courses;

    @OneToMany(mappedBy = "id.user", cascade = CascadeType.REMOVE)
    private List<Registrations> registers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}