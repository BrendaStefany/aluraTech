package br.com.brendaStefany.aluraTech.dto.users;

import br.com.brendaStefany.aluraTech.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private String name;
    private String email;
    private String role;

    public UsersDTO(Users savedUser) {
        this.name = savedUser.getName();
        this.email = savedUser.getEmail();
        this.role = savedUser.getRole();
    }
}
