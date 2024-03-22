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
public class UsersWithUsernameDTO extends UsersDTO {
    private String username;

    public UsersWithUsernameDTO(Users savedUser) {
        super(savedUser);
        this.username = savedUser.getUsername();
    }
}
