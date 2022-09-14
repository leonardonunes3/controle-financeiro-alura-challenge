package br.com.alura.controlefinanceiro.service;

import br.com.alura.controlefinanceiro.dto.DespesaDto;
import br.com.alura.controlefinanceiro.exceptions.ValidacaoException;
import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.repository.DespesaRepository;
import br.com.alura.controlefinanceiro.service.impl.DespesaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DespesaServiceTest {

    @InjectMocks
    DespesaServiceImpl despesaService;

    @Mock
    DespesaRepository despesaRepository;

    @Test
    public void testSave() throws Exception {
        DespesaDto despesaDto = new DespesaDto("descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);

        Mockito.when(despesaRepository.save(Mockito.any(Despesa.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(despesaRepository.findByDescricao(Mockito.any())).thenReturn(null);

        Despesa despesaRetorno = despesaService.save(despesaDto);

        verify(despesaRepository, times(1)).save(Mockito.any());
        verify(despesaRepository, times(1)).findByDescricao(Mockito.any());
        assertEquals(Despesa.categoria.Alimentacao, despesaRetorno.getCategoria());
    }

    @Test
    public void testSaveSetCategoriaOutros() throws Exception {
        Despesa despesa1 = new Despesa(1L, "descricao1", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        Despesa despesa2 = new Despesa(2L, "descricao2", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        List<Despesa> listaDespesaExistente = Arrays.asList(despesa1, despesa2);

        DespesaDto despesaDto = new DespesaDto();
        despesaDto.setDescricao("descricao");
        despesaDto.setValor(BigDecimal.ONE);
        despesaDto.setData(LocalDate.now());

        Mockito.when(despesaRepository.save(Mockito.any(Despesa.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(despesaRepository.findByDescricao(Mockito.any())).thenReturn(listaDespesaExistente);

        Despesa despesaRetorno = despesaService.save(despesaDto);

        verify(despesaRepository, times(1)).save(Mockito.any());
        verify(despesaRepository, times(1)).findByDescricao(Mockito.any());
        assertEquals(Despesa.categoria.Outras, despesaRetorno.getCategoria());
    }

    @Test
    public void testSaveException() throws Exception {
        Despesa despesa1 = new Despesa(1L, "descricao1", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        Despesa despesa2 = new Despesa(2L, "descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        List<Despesa> listaDespesaExistente = Arrays.asList(despesa1, despesa2);

        DespesaDto despesaDto = new DespesaDto("descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);

        Mockito.when(despesaRepository.findByDescricao(Mockito.any())).thenReturn(listaDespesaExistente);

        assertThrows(ValidacaoException.class, () -> despesaService.save(despesaDto));
        verify(despesaRepository, times(0)).save(Mockito.any());
        verify(despesaRepository, times(1)).findByDescricao(Mockito.any());
    }

    @Test
    public void testFindAll() throws Exception {
        Despesa despesa1 = new Despesa(1L, "descricao1", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        Despesa despesa2 = new Despesa(2L, "descricao", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        List<Despesa> despesaList = Arrays.asList(despesa1, despesa2);

        Mockito.when(despesaRepository.findAll()).thenReturn(despesaList);

        List<Despesa> listDespesaRetorno = despesaService.findAllByDescricao(null);

        verify(despesaRepository, times(1)).findAll();
        verify(despesaRepository, times(0)).findByDescricaoContaining(Mockito.any());
    }

    @Test
    public void testFindAllByDescricao() throws Exception {
        Despesa despesa1 = new Despesa(1L, "descricao1", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        List<Despesa> despesaList = List.of(despesa1);

        Mockito.when(despesaRepository.findByDescricaoContaining("descricao1")).thenReturn(despesaList);

        List<Despesa> listDespesaRetorno = despesaService.findAllByDescricao("descricao1");

        verify(despesaRepository, times(0)).findAll();
        verify(despesaRepository, times(1)).findByDescricaoContaining(Mockito.any());
    }

    @Test
    public void testFindById() throws Exception {
        Despesa despesa = new Despesa(1L, "descricao1", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);

        Mockito.when(despesaRepository.findById(1L)).thenReturn(Optional.of(despesa));

        assertDoesNotThrow(() -> despesaService.findById(1L));
    }

    @Test
    public void testFindByIdException() throws Exception {
        Mockito.when(despesaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ValidacaoException.class, () -> despesaService.findById(1L));
    }

    @Test
    public void testUpdate() throws Exception {
        Despesa despesa = new Despesa(1L, "descricao1", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        Despesa despesaEntrada = new Despesa(1L, "descricaoNova", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Transporte);

        Mockito.when(despesaRepository.findById(1L)).thenReturn(Optional.of(despesa));
        Mockito.when(despesaRepository.save(Mockito.any(Despesa.class))).thenAnswer(i -> i.getArguments()[0]);

        Despesa despesaRetorno = despesaService.update(despesaEntrada, 1L);

        assertEquals(despesaRetorno.getDescricao(), "descricaoNova");
        assertEquals(despesaRetorno.getCategoria(), Despesa.categoria.Transporte);
    }

    @Test
    public void testUpdateException() throws Exception {
        Despesa despesa = new Despesa(1L, "descricao1", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);

        Mockito.when(despesaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ValidacaoException.class, () -> despesaService.update(despesa,1L));
    }

    @Test
    public void testFindAllByAnoMes() throws Exception {
        Despesa despesa = new Despesa(1L, "descricao1", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        Despesa despesa2 = new Despesa(2L, "descricao1", BigDecimal.ONE, LocalDate.now(), Despesa.categoria.Alimentacao);
        List<Despesa> despesaList = List.of(despesa, despesa2);

        Mockito.when(despesaRepository.findAllByDataBetween(Mockito.any(), Mockito.any())).thenReturn(despesaList);

        List<DespesaDto> despesaDtoRetornoList = despesaService.findAllByAnoMes(2022, 8);

        assertEquals(despesaDtoRetornoList.size(), 2);
    }
}
