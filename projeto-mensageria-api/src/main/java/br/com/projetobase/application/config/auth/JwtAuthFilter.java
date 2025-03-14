package br.com.projetobase.application.config.auth;

import br.com.projetobase.domain.exception.TokenInvalidoException;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private CustomUserDetailService customUserDetailService;
    private Environment environment;
    private static final String URL_LOGIN = "/login";
    private static final String URL_USUARIOS_POST = "/usuarios";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {


        if (verificarSeEhRotaPublica(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = jwtService.getToken(httpServletRequest);

        inserirUsuarioNoContexto(httpServletRequest, token);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void inserirUsuarioNoContexto(HttpServletRequest httpServletRequest, String token) {
        if (!jwtService.tokenValido(token)) {
            throw new TokenInvalidoException();
        }

        String login = jwtService.obterLoginUsuario(token);
        UserDetails usuario = customUserDetailService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken usuarioAutenticado = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        usuarioAutenticado.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);
    }

    private boolean verificarSeEhRotaPublica(HttpServletRequest httpServletRequest) {
        if(environment.getProperty("spring.profiles.active").equals("test")) return true;

        return ehRotaLogin(httpServletRequest)
                || ehRotaUsuariosPost(httpServletRequest);
    }

    private boolean ehRotaLogin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI().equals(URL_LOGIN);
    }

    private boolean ehRotaUsuariosPost(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getMethod().equals(HttpMethod.POST.name()) && httpServletRequest.getRequestURI().equals(URL_USUARIOS_POST);
    }

}
