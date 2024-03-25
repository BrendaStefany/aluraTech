package br.com.brendaStefany.aluraTech.dto.users;
import br.com.brendaStefany.aluraTech.domain.Users;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersOutboundDTO {

    private String message;
    private UsersDTO data;

    public UsersOutboundDTO(String message, Users savedUser) {
        this.message = message;
        this.data = new UsersDTO(savedUser);
    }
}