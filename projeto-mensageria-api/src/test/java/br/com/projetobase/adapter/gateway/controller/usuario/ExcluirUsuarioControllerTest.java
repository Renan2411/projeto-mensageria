package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.adapter.gateway.dataprovider.usuario.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/migration/datasets/usuario/excluir-usario.sql")
public class ExcluirUsuarioControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Rollback
    @Transactional
    public void deveExcluirUsuarioComSucessoTest() throws Exception {
        mockMvc.perform(delete("/usuarios/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(usuarioRepository.existsById(1L));
    }

}