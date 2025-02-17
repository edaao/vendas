package projeto.vendas.Infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import projeto.vendas.domain.usuario.Usuario;

import java.time.Instant;

@Service
public class TokenService {

    @Value("$api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {

        try{
            var algoritimo = Algorithm.HMAC256(secret.trim());
            return JWT.create()
                    .withIssuer("API projeto.vendas")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(Instant.now().plusSeconds(3600))
                    .sign(algoritimo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt ", exception);
        }
    }

    public String getSubject(String tokenJWT) {

        try {
            var algoritimo = Algorithm.HMAC256(secret.trim());
            return JWT.require(algoritimo)
                    .withIssuer("API projeto.vendas")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!",exception);
        }
    }
}
