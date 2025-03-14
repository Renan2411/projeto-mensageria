package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.domain.exception.UsuarioNaoEncontradoException;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/migration/datasets/usuario/buscar-por-id-usario.sql")
public class BuscarUsuarioPorIdControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Rollback
    @Transactional
    public void deveBuscarUsuarioPorIdComSucessoTest() throws Exception {
        mockMvc.perform(get("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.nome", equalTo("Teste")))
                .andExpect(jsonPath("$.email", equalTo("teste@gmail.com")))
                .andExpect(jsonPath("$.cpf", equalTo("80220885443")))
                .andExpect(jsonPath("$.dataNascimento", equalTo("2024-11-06T10:30:45-04:00")));
    }

    @Test
    @Rollback
    @Transactional
    public void deveFalharAoBuscarUsuarioPorIdCasoUsuarioNaoExistaTest() throws Exception {
        mockMvc.perform(get("/usuarios/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Usuário de id 99 não encontrado.")))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof UsuarioNaoEncontradoException));
    }

}