package br.com.brendaStefany.aluraTech.service;

import br.com.brendaStefany.aluraTech.domain.Users;
import br.com.brendaStefany.aluraTech.dto.users.UsersDTO;
import br.com.brendaStefany.aluraTech.dto.users.UsersOutboundDTO;
import br.com.brendaStefany.aluraTech.dto.users.UsersWithUsernameDTO;
import br.com.brendaStefany.aluraTech.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsersOutboundDTO addNewUser(Users user) {
        try {
            Optional<Users> existingUserByEmail = usersRepository.findByEmail(user.getEmail());
            if (existingUserByEmail.isPresent()) {
                throw new IllegalStateException("User with this email already exists.");
            }

            Optional<Users> existingUserByUsername = usersRepository.findByUsername(user.getUsername());
            if (existingUserByUsername.isPresent()) {
                throw new IllegalStateException("User with this username already exists.");
            }


            if (user.getPassword().length() <= 10) {
                String encryptedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encryptedPassword);
            } else {
                throw new RuntimeException("The password must be a maximum of 10 characters.");
            }

            Users savedUser = usersRepository.save(user);
            return new UsersOutboundDTO("User created with successfully!!",savedUser);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    public UsersDTO findUserByUsernameDTO(String username){
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for username: " + username));
        return new UsersDTO(user);
    }

    public List<UsersWithUsernameDTO> findAllUsers(){
        List<Users> users = usersRepository.findAll();

        List<UsersWithUsernameDTO> usersDTOs = users.stream()
                .map(UsersWithUsernameDTO::new)
                .collect(Collectors.toList());

        return usersDTOs;
    }

}
