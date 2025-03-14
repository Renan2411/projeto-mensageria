package br.com.projetobase.adapter.gateway.dataprovider.usuarioRole;

import br.com.projetobase.domain.entity.UsuarioRoleEntity;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioRoleDataProvider;
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
