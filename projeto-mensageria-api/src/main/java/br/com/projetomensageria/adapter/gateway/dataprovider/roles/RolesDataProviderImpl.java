package br.com.projetomensageria.adapter.gateway.dataprovider.roles;

import br.com.projetomensageria.domain.entity.RolesEntity;
import br.com.projetomensageria.domain.interfaces.dataprovider.IRolesDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RolesDataProviderImpl implements IRolesDataProvider {

    private RolesRepository rolesRepository;

    @Override
    public Optional<RolesEntity> buscarPorNome(RolesEntity.EnumRole nome) {
        return rolesRepository.findByNome(nome);
    }

    @Override
    public List<RolesEntity> buscarPorIdUsuario(Long idUsuario) {
        return rolesRepository.buscarPorIdUsuario(idUsuario);
    }

}
