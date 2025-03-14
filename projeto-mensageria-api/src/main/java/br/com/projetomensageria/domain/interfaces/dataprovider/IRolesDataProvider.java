package br.com.projetomensageria.domain.interfaces.dataprovider;

import br.com.projetomensageria.domain.entity.RolesEntity;

import java.util.List;
import java.util.Optional;

public interface IRolesDataProvider {

    Optional<RolesEntity> buscarPorNome(RolesEntity.EnumRole nome);

    List<RolesEntity> buscarPorIdUsuario(Long idUsuario);

}
