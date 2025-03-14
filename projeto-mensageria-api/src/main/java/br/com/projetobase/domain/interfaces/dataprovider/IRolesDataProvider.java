package br.com.projetobase.domain.interfaces.dataprovider;

import br.com.projetobase.domain.entity.RolesEntity;

import java.util.List;
import java.util.Optional;

public interface IRolesDataProvider {

    Optional<RolesEntity> buscarPorNome(RolesEntity.EnumRole nome);

    List<RolesEntity> buscarPorIdUsuario(Long idUsuario);

}
