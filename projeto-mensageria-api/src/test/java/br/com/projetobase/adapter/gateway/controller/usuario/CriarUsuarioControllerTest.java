package br.com.projetobase.adapter.gateway.controller.usuario;

import br.com.projetobase.adapter.gateway.dataprovider.usuarioRole.UsuarioRoleRepository;
import br.com.projetobase.domain.entity.RolesEntity;
import br.com.projetobase.domain.entity.UsuarioRoleEntity;
import br.com.projetobase.domain.exception.generic.GenericValidationException;
import br.com.projetobase.domain.exception.generic.GenericValidationExceptionList;
import br.com.projetobase.domain.interfaces.dataprovider.IUsuarioRoleDataProvider;
import br.com.projetobase.domain.usecase.usuario.criar.CriarUsuarioInput;
import br.com.projetobase.helper.JsonHelper;
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

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/migration/datasets/usuario/criar-usario.sql")
public class CriarUsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRoleRepository usuarioRoleRepository;

    @Test
    @Rollback
    @Transactional
    public void deveCriarUsuarioComSucessoTest() throws Exception {
        OffsetDateTime dataNascimento = OffsetDateTime.ofInstant(Instant.parse("2024-11-06T14:30:45Z"), ZoneId.systemDefault());

        CriarUsuarioInput entrada = CriarUsuarioInput.builder()
                .nome("Teste")
                .cpf("07441855721")
                .email("teste2@gmail.com")
                .senha("senha123")
                .dataNascimento(dataNascimento)
                .build();

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.toJson(entrada)))
                .andDo(print())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.nome", equalTo("Teste")))
                .andExpect(jsonPath("$.cpf", equalTo("07441855721")))
                .andExpect(jsonPath("$.email", equalTo("teste2@gmail.com")))
                .andExpect(jsonPath("$.dataNascimento", equalTo("2024-11-06T14:30:45Z")));
    }

    @Test
    @Rollback
    @Transactional
    public void deveFalharAoCriarUsuarioComCpfJaExistenteTest() throws Exception {
        OffsetDateTime dataNascimento = OffsetDateTime.ofInstant(Instant.parse("2024-11-06T14:30:45Z"), ZoneId.systemDefault());

        CriarUsuarioInput entrada = CriarUsuarioInput.builder()
                .nome("Teste")
                .cpf("80220885443")
                .email("teste2@gmail.com")
                .senha("senha123")
                .dataNascimento(dataNascimento)
                .build();

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.toJson(entrada)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", equalTo("Usuário com CPF 80220885443 já cadastrado.")))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof GenericValidationException));
    }

    @Test
    @Rollback
    @Transactional
    public void deveFalharAoCriarUsuarioComEmailJaExistenteTest() throws Exception {
        OffsetDateTime dataNascimento = OffsetDateTime.ofInstant(Instant.parse("2024-11-06T14:30:45Z"), ZoneId.systemDefault());

        CriarUsuarioInput entrada = CriarUsuarioInput.builder()
                .nome("Teste")
                .cpf("90220885443")
                .email("teste@gmail.com")
                .senha("senha123")
                .dataNascimento(dataNascimento)
                .build();

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.toJson(entrada)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", equalTo("Usuário com email teste@gmail.com já cadastrado.")))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof GenericValidationException));
    }

    @Test
    @Rollback
    @Transactional
    public void deveFalharAoCriarUsuarioCasoEntradaInvalidaTest() throws Exception {
        OffsetDateTime dataNascimento = OffsetDateTime.ofInstant(Instant.parse("2024-11-06T14:30:45Z"), ZoneId.systemDefault());

        CriarUsuarioInput entrada = CriarUsuarioInput.builder()
                .nome(null)
                .cpf(null)
                .email(null)
                .dataNascimento(dataNascimento)
                .build();

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.toJson(entrada)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagens[0].message", equalTo("Ausência do cpf." )))
                .andExpect(jsonPath("$.mensagens[1].message", equalTo("Ausência do nome.")))
                .andExpect(jsonPath("$.mensagens[2].message", equalTo("Ausência do email.")))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof GenericValidationExceptionList));
    }

}