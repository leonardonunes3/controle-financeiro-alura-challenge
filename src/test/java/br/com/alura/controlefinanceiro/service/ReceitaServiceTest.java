package br.com.alura.controlefinanceiro.service;

import br.com.alura.controlefinanceiro.dto.ReceitaDto;
import br.com.alura.controlefinanceiro.exceptions.ValidacaoException;
import br.com.alura.controlefinanceiro.model.Receita;
import br.com.alura.controlefinanceiro.repository.ReceitaRepository;
import br.com.alura.controlefinanceiro.service.impl.ReceitaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReceitaServiceTest {

    @InjectMocks
    ReceitaServiceImpl receitaService;

    @Mock
    ReceitaRepository receitaRepository;

    @Test
    public void testSave() throws Exception {
        ReceitaDto receitaDto = new ReceitaDto("descricao", BigDecimal.ONE, LocalDate.now());

        Mockito.when(receitaRepository.save(Mockito.any(Receita.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(receitaRepository.findByDescricao(Mockito.any())).thenReturn(null);

        Receita receitaRetorno = receitaService.save(receitaDto);

        verify(receitaRepository, times(1)).save(Mockito.any());
        verify(receitaRepository, times(1)).findByDescricao(Mockito.any());
        assertEquals("descricao", receitaRetorno.getDescricao());
    }

    @Test
    public void testSaveException() throws Exception {
        Receita receita1 = new Receita(1L, "descricao1", BigDecimal.ONE, LocalDate.now());
        Receita receita2 = new Receita(2L, "descricao", BigDecimal.ONE, LocalDate.now());
        List<Receita> listaReceitaExistente = Arrays.asList(receita1, receita2);

        ReceitaDto receitaDto = new ReceitaDto("descricao", BigDecimal.ONE, LocalDate.now());

        Mockito.when(receitaRepository.findByDescricao(Mockito.any())).thenReturn(listaReceitaExistente);

        assertThrows(ValidacaoException.class, () -> receitaService.save(receitaDto));
        verify(receitaRepository, times(0)).save(Mockito.any());
        verify(receitaRepository, times(1)).findByDescricao(Mockito.any());
    }

    @Test
    public void testFindAll() throws Exception {
        Receita receita1 = new Receita(1L, "descricao1", BigDecimal.ONE, LocalDate.now());
        Receita receita2 = new Receita(2L, "descricao", BigDecimal.ONE, LocalDate.now());
        List<Receita> receitaList = Arrays.asList(receita1, receita2);

        Mockito.when(receitaRepository.findAll()).thenReturn(receitaList);

        List<Receita> listReceitaRetorno = receitaService.findAllByDescricao(null);

        verify(receitaRepository, times(1)).findAll();
        verify(receitaRepository, times(0)).findByDescricaoContaining(Mockito.any());
    }

    @Test
    public void testFindAllByDescricao() throws Exception {
        Receita receita1 = new Receita(1L, "descricao1", BigDecimal.ONE, LocalDate.now());
        List<Receita> receitaList = List.of(receita1);

        Mockito.when(receitaRepository.findByDescricaoContaining("descricao1")).thenReturn(receitaList);

        List<Receita> listReceitaRetorno = receitaService.findAllByDescricao("descricao1");

        verify(receitaRepository, times(0)).findAll();
        verify(receitaRepository, times(1)).findByDescricaoContaining(Mockito.any());
    }

    @Test
    public void testFindById() throws Exception {
        Receita receita = new Receita(1L, "descricao1", BigDecimal.ONE, LocalDate.now());

        Mockito.when(receitaRepository.findById(1L)).thenReturn(Optional.of(receita));

        assertDoesNotThrow(() -> receitaService.findById(1L));
    }

    @Test
    public void testFindByIdException() throws Exception {
        Mockito.when(receitaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ValidacaoException.class, () -> receitaService.findById(1L));
    }

    @Test
    public void testUpdate() throws Exception {
        Receita receita = new Receita(1L, "descricao1", BigDecimal.ONE, LocalDate.now());
        Receita receitaEntrada = new Receita(1L, "descricaoNova", BigDecimal.TEN, LocalDate.now());

        Mockito.when(receitaRepository.findById(1L)).thenReturn(Optional.of(receita));
        Mockito.when(receitaRepository.save(Mockito.any(Receita.class))).thenAnswer(i -> i.getArguments()[0]);

        Receita receitaRetorno = receitaService.update(receitaEntrada, 1L);

        assertEquals(receitaRetorno.getDescricao(), "descricaoNova");
        assertEquals(receitaRetorno.getValor(), BigDecimal.TEN);
    }

    @Test
    public void testUpdateException() throws Exception {
        Receita receita = new Receita(1L, "descricao1", BigDecimal.ONE, LocalDate.now());

        Mockito.when(receitaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ValidacaoException.class, () -> receitaService.update(receita,1L));
    }

    @Test
    public void testFindAllByAnoMes() throws Exception {
        Receita receita = new Receita(1L, "descricao1", BigDecimal.ONE, LocalDate.now());
        Receita receita2 = new Receita(1L, "descricao1", BigDecimal.ONE, LocalDate.now());
        List<Receita> receitaList = List.of(receita, receita2);

        Mockito.when(receitaRepository.findAllByDataBetween(Mockito.any(), Mockito.any())).thenReturn(receitaList);

        List<ReceitaDto> receitaDtoRetornoList = receitaService.findAllByAnoMes(2022, 8);

        assertEquals(receitaDtoRetornoList.size(), 2);
    }
}
