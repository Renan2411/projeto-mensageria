package br.com.projetomensageria.adapter.gateway.dataprovider.mensagem;

import br.com.projetomensageria.domain.entity.MensagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends JpaRepository<MensagemEntity, Long> {
}
