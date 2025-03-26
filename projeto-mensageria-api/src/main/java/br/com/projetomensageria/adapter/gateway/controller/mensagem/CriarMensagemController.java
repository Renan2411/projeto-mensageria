package br.com.projetomensageria.adapter.gateway.controller.mensagem;

import br.com.projetomensageria.domain.usecase.mensagem.criar.CriarMensagemInput;
import br.com.projetomensageria.domain.usecase.mensagem.criar.CriarMensagemUseCase;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Mensagens", tags = "Mensagens", description = "Manutenção das mensagens")
@AllArgsConstructor
@RestController
@RequestMapping("/mensagens")
public class CriarMensagemController {

    private final CriarMensagemUseCase useCase;

    @PostMapping
    private ResponseEntity<Void> criar(@RequestBody CriarMensagemInput entrada) throws InterruptedException {
        useCase.executar(entrada);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
