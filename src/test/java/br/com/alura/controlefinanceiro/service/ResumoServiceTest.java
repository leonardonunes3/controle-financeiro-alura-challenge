package br.com.alura.controlefinanceiro.service;

import br.com.alura.controlefinanceiro.dto.DespesaDto;
import br.com.alura.controlefinanceiro.dto.ReceitaDto;
import br.com.alura.controlefinanceiro.dto.ResumoDto;
import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.model.Receita;
import br.com.alura.controlefinanceiro.service.impl.DespesaServiceImpl;
import br.com.alura.controlefinanceiro.service.impl.ReceitaServiceImpl;
import br.com.alura.controlefinanceiro.service.impl.ResumoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ResumoServiceTest {

    @InjectMocks
    ResumoServiceImpl resumoService;

    @Mock
    ReceitaServiceImpl receitaService;

    @Mock
    DespesaServiceImpl despesaService;

    @Test
    public void testCalculaResumo() throws Exception {
        ReceitaDto receitaDto = new ReceitaDto("descricao", BigDecimal.valueOf(150), LocalDate.now());
        ReceitaDto receitaDto2 = new ReceitaDto("descricao", BigDecimal.valueOf(250), LocalDate.now());
        DespesaDto despesaDto = new DespesaDto("descricao", BigDecimal.valueOf(100), LocalDate.now(), Despesa.categoria.Outras);
        DespesaDto despesaDto2 = new DespesaDto("descricao", BigDecimal.valueOf(10), LocalDate.now(), Despesa.categoria.Outras);
        List<ReceitaDto> receitaDtoList = List.of(receitaDto, receitaDto2);
        List<DespesaDto> despesaDtoList = List.of(despesaDto, despesaDto2);

        Mockito.when(receitaService.findAllByAnoMes(Mockito.any(), Mockito.any())).thenReturn(receitaDtoList);
        Mockito.when(despesaService.findAllByAnoMes(Mockito.any(), Mockito.any())).thenReturn(despesaDtoList);

        ResumoDto resumoDto = resumoService.calculaResumo(2022, 10);

        assertEquals(resumoDto.getSaldo(), BigDecimal.valueOf(290));
    }

    @Test
    public void testCalculaResumoEmpty() throws Exception {
        List<ReceitaDto> receitaDtoList = Collections.emptyList();
        List<DespesaDto> despesaDtoList = Collections.emptyList();

        Mockito.when(receitaService.findAllByAnoMes(Mockito.any(), Mockito.any())).thenReturn(receitaDtoList);
        Mockito.when(despesaService.findAllByAnoMes(Mockito.any(), Mockito.any())).thenReturn(despesaDtoList);

        ResumoDto resumoDto = resumoService.calculaResumo(2022, 10);

        assertEquals(resumoDto.getSaldo(), BigDecimal.ZERO);
    }
}
