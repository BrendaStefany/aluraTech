package br.com.brendaStefany.aluraTech.controller;

import br.com.brendaStefany.aluraTech.dto.login.Login;
import br.com.brendaStefany.aluraTech.infra.security.TokenJWTResponse;
import br.com.brendaStefany.aluraTech.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity login (@RequestBody @Valid Login data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken(authentication.getName());
        return ResponseEntity.ok(new TokenJWTResponse(tokenJWT));
    }

}
