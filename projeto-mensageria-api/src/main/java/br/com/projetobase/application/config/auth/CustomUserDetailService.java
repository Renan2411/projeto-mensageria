package br.com.projetobase.application.config.auth;

import br.com.projetobase.domain.entity.RolesEntity;
import br.com.projetobase.domain.entity.UsuarioEntity;
import br.com.projetobase.domain.exception.UsuarioNaoEncontradoException;
import br.com.projetobase.domain.interfaces.dataprovider.IRolesDataProvider;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final IUsuarioDataProvider iUsuarioDataProvider;
    private final IRolesDataProvider iRolesDataProvider;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = iUsuarioDataProvider.buscarPorEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Email", email));
        List<String> roles = new ArrayList<>();
        List<RolesEntity> userRoles = iRolesDataProvider.buscarPorIdUsuario(usuarioEntity.getId());

        userRoles.forEach(userRole -> roles.add(userRole.getNome().name()));

        return User.builder()
                .username(usuarioEntity.getNome())
                .password(usuarioEntity.getSenha())
                .roles(roles.toArray(new String[0]))
                .build();
    }

    public UserDetails loadUserTest() {
        return User.builder()
                .username("User Test")
                .password("senha123")
                .roles(String.valueOf(List.of(RolesEntity.EnumRole.USUARIO_COMUM, RolesEntity.EnumRole.ADMINISTRADOR)))
                .build();
    }

}
