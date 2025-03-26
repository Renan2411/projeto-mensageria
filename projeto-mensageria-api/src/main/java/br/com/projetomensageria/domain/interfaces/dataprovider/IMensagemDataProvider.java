package br.com.projetomensageria.domain.interfaces.dataprovider;

import br.com.projetomensageria.domain.entity.MensagemEntity;

public interface IMensagemDataProvider {

    MensagemEntity salvar(MensagemEntity mensagemEntity);

    MensagemEntity editar(MensagemEntity mensagemEntity);
}
