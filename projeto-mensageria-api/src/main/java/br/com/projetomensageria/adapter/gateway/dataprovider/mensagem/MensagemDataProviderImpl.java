package br.com.projetomensageria.adapter.gateway.dataprovider.mensagem;

import br.com.projetomensageria.domain.entity.MensagemEntity;
import br.com.projetomensageria.domain.interfaces.dataprovider.IMensagemDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MensagemDataProviderImpl implements IMensagemDataProvider {

    private MensagemRepository mensagemRepository;

    @Override
    public MensagemEntity salvar(MensagemEntity mensagemEntity) {
        return mensagemRepository.save(mensagemEntity);
    }

    @Override
    public MensagemEntity editar(MensagemEntity mensagemEntity) {
        return mensagemRepository.save(mensagemEntity);
    }
}
