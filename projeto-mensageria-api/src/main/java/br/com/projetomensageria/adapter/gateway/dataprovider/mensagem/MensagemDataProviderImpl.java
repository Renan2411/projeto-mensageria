package br.com.projetomensageria.adapter.gateway.dataprovider.mensagem;

import br.com.projetomensageria.domain.interfaces.dataprovider.IMensagemDataProvider;
import org.springframework.stereotype.Component;

@Component
public class MensagemDataProviderImpl implements IMensagemDataProvider {

    private MensagemRepository mensagemRepository;

}
