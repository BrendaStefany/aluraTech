package br.com.brendaStefany.aluraTech.dto.registrations;

import br.com.brendaStefany.aluraTech.domain.Registrations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationsOutboundDTO {

    private String message;
    private RegistrationData data;

    public RegistrationsOutboundDTO(String message, Registrations savedRegister) {
        this.message = message;
        this.data = new RegistrationData();
        this.data.setUser(savedRegister.getId().getUser().getUsername());
        this.data.setCourse(savedRegister.getId().getCourse().getCode());
        this.data.setRegistration_date(savedRegister.getId().getUser().getCreation_date());
    }

    @Getter
    @Setter
    public class RegistrationData {
        private String user;
        private String course;
        private LocalDateTime registration_date;
    }
}