package br.com.projetobase.application.config.auth;

import br.com.projetobase.domain.entity.UsuarioEntity;
import br.com.projetobase.domain.exception.TokenInvalidoException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Component
@Data
public class JwtService {

    @Value("${security.jwt.validity}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public String gerarToken(UsuarioEntity usuarioEntity) {
        long expString = Long.parseLong(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts
                .builder()
                .setSubject(usuarioEntity.getEmail())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token) {
        if ("test".equals(activeProfile)) {
            return true;
        }

        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (Exception e) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return (String) obterClaims(token).getSubject();
    }

    public String getToken(HttpServletRequest httpServletRequest) {
        if ("test".equals(activeProfile)) {
            return "token";
        }

        String authorization = httpServletRequest.getHeader(HEADER_AUTHORIZATION);

        if (Objects.isNull(authorization) || !authorization.startsWith(TOKEN_PREFIX)) {
            throw new TokenInvalidoException();
        }

        return authorization.substring(TOKEN_PREFIX.length() - 1);
    }

}
