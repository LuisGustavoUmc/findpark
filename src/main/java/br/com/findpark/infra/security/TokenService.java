package br.com.findpark.infra.security;

import br.com.findpark.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    Algorithm algorithm = null;

    public String gerarToken(Usuario usuario){
        try{
            algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("findpark-auth")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validarToken(String token){
        try {
            algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("findpark-auth")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    public String gerarRefreshToken(Usuario usuario) {
        try {
            algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("findpark-auth")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"))) // 7 dias
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar refresh token", exception);
        }
    }

    private Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
