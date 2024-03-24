package br.com.brendaStefany.aluraTech.dto.registrations;

import br.com.brendaStefany.aluraTech.domain.Registrations;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationsDTO {

    private String message;
    private RegistrationData data;

    public RegistrationsDTO(String message, Registrations savedUser) {
        this.message = message;
        this.data = new RegistrationData();
        this.data.setUser(savedUser.getRegister_user().getUsername());
        this.data.setCourse(savedUser.getRegister_course().getCode());
        this.data.setRegistration_date(savedUser.getRegistration_date());
    }

    @Getter
    @Setter
    public class RegistrationData {
        private String user;
        private String course;
        private LocalDateTime registration_date;
    }
}
