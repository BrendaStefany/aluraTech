package br.com.brendaStefany.aluraTech.service;

import br.com.brendaStefany.aluraTech.repository.UsersRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    UsersRepository usersRepository;
    public String generateToken(String username) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            var user = usersRepository.findByUsername(username);
            return JWT.create()
                    .withIssuer("API aluraTech")
                    .withSubject(username)
                    .withExpiresAt(dateExpires())
                    //.withClaim("role", user.get().getRole())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error generate token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API aluraTech")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalid or expired!!");
        }
    }

    private Instant dateExpires(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
