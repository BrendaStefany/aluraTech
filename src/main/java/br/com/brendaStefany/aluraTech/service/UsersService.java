package br.com.brendaStefany.aluraTech.service;

import br.com.brendaStefany.aluraTech.domain.Users;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import br.com.brendaStefany.aluraTech.dto.users.UsersWithUsernameDTO;
import br.com.brendaStefany.aluraTech.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public UsersDTO addNewUser(Users user) {
        try {
            Optional<Users> existingUserByEmail = usersRepository.findByEmail(user.getEmail());
            if (existingUserByEmail.isPresent()) {
                throw new IllegalStateException("User with this email already exists.");
            }

            Optional<Users> existingUserByUsername = usersRepository.findByUsername(user.getUsername());
            if (existingUserByUsername.isPresent()) {
                throw new IllegalStateException("User with this username already exists.");
            }

            user.setCreation_date(LocalDateTime.now());

            Users savedUser = usersRepository.save(user);
            return new UsersDTO(savedUser);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    public UsersDTO findUserByUsername(String username){
        Optional<Users> existingUserByUsername = usersRepository.findByUsername(username);
        return existingUserByUsername.map(UsersDTO::new).orElse(null);
    }

    public List<UsersWithUsernameDTO> findAllUsers(){
        List<Users> users = usersRepository.findAll();

        List<UsersWithUsernameDTO> usersDTOs = users.stream()
                .map(UsersWithUsernameDTO::new)
                .collect(Collectors.toList());

        return usersDTOs;
    }

}
