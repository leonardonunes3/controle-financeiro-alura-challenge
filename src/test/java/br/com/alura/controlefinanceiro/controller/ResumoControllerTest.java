package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.dto.ResumoDto;
import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.service.ResumoService;
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
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ResumoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ResumoControllerTest {
    
    @MockBean
    ResumoService resumoService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testFindAllByAnoMes() throws Exception {
        HashMap<String, BigDecimal> valorDespesasCategorias = new HashMap<>();
        valorDespesasCategorias.put(Despesa.categoria.Transporte.name(), BigDecimal.ONE);

        ResumoDto resumoDto = new ResumoDto(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ZERO, valorDespesasCategorias);

        Mockito.when(resumoService.calculaResumo(1, 10)).thenReturn(resumoDto);

        mockMvc.perform(get("/resumo/{ano}/{mes}", 1, 10)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.saldo").value(BigDecimal.ZERO));
    }
}
