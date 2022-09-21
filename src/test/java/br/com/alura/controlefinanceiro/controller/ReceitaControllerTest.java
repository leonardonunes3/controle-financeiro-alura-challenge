package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.dto.ReceitaDto;
import br.com.alura.controlefinanceiro.model.Receita;
import br.com.alura.controlefinanceiro.service.ReceitaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReceitaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ReceitaControllerTest {

    @MockBean
    ReceitaService receitaService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testFindAll() throws Exception {
        Receita receita = new Receita(1L, "descricao", BigDecimal.ONE, LocalDate.now());
        Receita receita2 = new Receita(2L, "descricao", BigDecimal.ONE, LocalDate.now());
        List<Receita> receitaList = Arrays.asList(receita, receita2);

        Mockito.when(receitaService.findAllByDescricao("descricao")).thenReturn(receitaList);

        mockMvc.perform(get("/receitas").param("descricao", "descricao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].descricao", Matchers.is("descricao")));
    }

    @Test
    public void testSave() throws Exception {
        Receita receita = new Receita(1L, "descricao", BigDecimal.ONE, LocalDate.now());
        ReceitaDto receitaDto = new ReceitaDto("descricao", BigDecimal.ONE, LocalDate.now());

        Mockito.when(receitaService.save(receitaDto)).thenReturn(receita);

        mockMvc.perform(post("/receitas")
                        .content(asJsonString(receitaDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value("descricao"))
                .andExpect(jsonPath("$.valor").value(BigDecimal.ONE));
    }

    @Test
    public void testFindById() throws Exception {
        Receita receita = new Receita(1L, "descricao", BigDecimal.ONE, LocalDate.now());

        Mockito.when(receitaService.findById(1L)).thenReturn(receita);

        mockMvc.perform(get("/receitas/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    public void testUpdate() throws Exception {
        Receita receita = new Receita(2L, "descricao", BigDecimal.ONE, LocalDate.now());

        Mockito.when(receitaService.update(receita,2L)).thenReturn(receita);

        mockMvc.perform(put("/receitas/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(receita))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L));
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/receitas/{id}", 2))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAllByAnoMes() throws Exception {
        ReceitaDto receitaDto = new ReceitaDto("descricao", BigDecimal.ONE, LocalDate.now());
        List<ReceitaDto> receitaList = Arrays.asList(receitaDto);

        Mockito.when(receitaService.findAllByAnoMes(1, 10)).thenReturn(receitaList);

        mockMvc.perform(get("/receitas/{ano}/{mes}", 1, 10)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descricao").value("descricao"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().findAndRegisterModules().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
