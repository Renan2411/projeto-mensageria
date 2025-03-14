package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.application.config.auth.CustomUserDetailService;
import br.com.projetobase.application.config.auth.JwtService;
import br.com.projetobase.domain.entity.RolesEntity;
import br.com.projetobase.domain.exception.UsuarioNaoEncontradoException;
import br.com.projetobase.domain.exception.generic.GenericValidationException;
import br.com.projetobase.domain.exception.generic.GenericValidationExceptionList;
import br.com.projetobase.domain.usecase.usuario.editar.EditarUsuarioInput;
import br.com.projetobase.helper.JsonHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/migration/datasets/usuario/editar-usario.sql")
public class EditarUsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Rollback
    @Transactional
    public void deveEditarUmUsuarioComSucessoTest() throws Exception {
        EditarUsuarioInput entrada = EditarUsuarioInput.builder()
                .nome("Editado")
                .email("editado@gmail.com")
                .dataNascimento(OffsetDateTime.of(LocalDateTime.of(2002, 11, 24, 8, 0, 0), ZoneOffset.UTC))
                .build();

        mockMvc.perform(put("/usuarios/1")
                        .content(JsonHelper.toJson(entrada))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.nome", equalTo("Editado")))
                .andExpect(jsonPath("$.cpf", equalTo("80220885443")))
                .andExpect(jsonPath("$.email", equalTo("editado@gmail.com")))
                .andExpect(jsonPath("$.dataNascimento", equalTo("2002-11-24T08:00:00Z")));
    }

    @Test
    @Rollback
    @Transactional
    public void deveFalharAoEditarUmUsuarioCasoNaoExistaTest() throws Exception {
        EditarUsuarioInput entrada = EditarUsuarioInput.builder()
                .nome("Editado")
                .email("editado@gmail.com")
                .dataNascimento(OffsetDateTime.of(LocalDateTime.of(2002, 11, 24, 8, 0, 0), ZoneOffset.UTC))
                .build();

        mockMvc.perform(put("/usuarios/99")
                        .content(JsonHelper.toJson(entrada))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Usuário de id 99 não encontrado.")))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof UsuarioNaoEncontradoException));
    }

    @Test
    @Rollback
    @Transactional
    public void deveFalharAoEditarUmUsuarioCasoEmailJaEmUsoTest() throws Exception {
        EditarUsuarioInput entrada = EditarUsuarioInput.builder()
                .nome("Editado")
                .email("teste@gmail.com")
                .dataNascimento(OffsetDateTime.of(LocalDateTime.of(2002, 11, 24, 8, 0, 0), ZoneOffset.UTC))
                .build();

        mockMvc.perform(put("/usuarios/2")
                        .content(JsonHelper.toJson(entrada))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", equalTo("Usuário com email teste@gmail.com já cadastrado.")))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof GenericValidationException));
    }

    @Transactional
    @Rollback
    @Test
    public void deveFalharAoEditarUmUsuarioCasoEntradaInvalidaTest() throws Exception {
        EditarUsuarioInput entrada = EditarUsuarioInput.builder()
                .nome("Editado")
                .email(null)
                .dataNascimento(OffsetDateTime.of(LocalDateTime.of(2002, 11, 24, 8, 0, 0), ZoneOffset.UTC))
                .build();

        mockMvc.perform(put("/usuarios/99")
                        .content(JsonHelper.toJson(entrada))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagens[0].message", equalTo("Ausência do email do usuário.")))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof GenericValidationExceptionList));
    }
}