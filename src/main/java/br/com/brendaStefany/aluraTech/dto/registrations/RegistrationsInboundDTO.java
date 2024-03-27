package br.com.brendaStefany.aluraTech.dto.registrations;

import br.com.brendaStefany.aluraTech.domain.Courses;
import br.com.brendaStefany.aluraTech.domain.Users;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationsInboundDTO {

    private Users users;
    private Courses courses;

}
