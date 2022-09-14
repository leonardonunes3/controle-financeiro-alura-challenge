package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.dto.DespesaDto;
import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.service.DespesaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DespesaController.class)
public class DespesaControllerTest {

    @MockBean
    DespesaService despesaService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testFindAll() throws Exception {
        Despesa despesa = new Despesa(1L, "descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Outras);
        Despesa despesa2 = new Despesa(2L, "descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        List<Despesa> despesaList = Arrays.asList(despesa, despesa2);

        Mockito.when(despesaService.findAllByDescricao("descricao")).thenReturn(despesaList);

        mockMvc.perform(get("/despesas").param("descricao", "descricao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].descricao", Matchers.is("descricao")));
    }

    @Test
    public void testSave() throws Exception {
        Despesa despesa = new Despesa(1L, "descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Outras);
        DespesaDto despesaDto = new DespesaDto("descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Outras);

        Mockito.when(despesaService.save(despesaDto)).thenReturn(despesa);

        mockMvc.perform(post("/despesas")
                .content(asJsonString(despesaDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value("descricao"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(BigDecimal.ONE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoria").value("Outras"));
    }

    @Test
    public void testFindById() throws Exception {
        Despesa despesa = new Despesa(1L, "descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Outras);

        Mockito.when(despesaService.findById(1L)).thenReturn(despesa);

        mockMvc.perform(get("/despesas/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    public void testUpdate() throws Exception {
        Despesa despesa = new Despesa(2L, "descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);

        Mockito.when(despesaService.update(despesa,2L)).thenReturn(despesa);

        mockMvc.perform(put("/despesas/{id}", 2)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(despesa))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L));
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/despesas/{id}", 2))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAllByAnoMes() throws Exception {
        DespesaDto despesaDto = new DespesaDto("descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Outras);
        List<DespesaDto> despesaList = Arrays.asList(despesaDto);

        Mockito.when(despesaService.findAllByAnoMes(1, 10)).thenReturn(despesaList);

        mockMvc.perform(get("/despesas/{ano}/{mes}", 1, 10)
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
