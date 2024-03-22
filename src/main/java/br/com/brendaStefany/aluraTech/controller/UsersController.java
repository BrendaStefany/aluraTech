package br.com.brendaStefany.aluraTech.controller;

import br.com.brendaStefany.aluraTech.domain.Users;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import br.com.brendaStefany.aluraTech.dto.users.UsersWithUsernameDTO;
import br.com.brendaStefany.aluraTech.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping()
    public ResponseEntity<UsersDTO> addNewUser(@RequestBody @Valid Users user){
        UsersDTO userDTO = usersService.addNewUser(user);
        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        UsersDTO userDTO = usersService.findUserByUsername(username);
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Username '" + username + "' não encontrado");
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UsersWithUsernameDTO>> getUsers() {
        List<UsersWithUsernameDTO> usersDTO = usersService.findAllUsers();
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

}
