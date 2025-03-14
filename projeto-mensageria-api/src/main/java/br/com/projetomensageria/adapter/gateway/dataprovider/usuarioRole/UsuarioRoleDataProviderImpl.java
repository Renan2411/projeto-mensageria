package br.com.projetomensageria.adapter.gateway.dataprovider.usuarioRole;

import br.com.projetomensageria.domain.entity.UsuarioRoleEntity;
import br.com.projetomensageria.domain.interfaces.dataprovider.IUsuarioRoleDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioRoleDataProviderImpl implements IUsuarioRoleDataProvider {

    private UsuarioRoleRepository usuarioRoleRepository;

    @Override
    public UsuarioRoleEntity criar(UsuarioRoleEntity usuarioRoleEntity) {
        return usuarioRoleRepository.save(usuarioRoleEntity);
    }
}
