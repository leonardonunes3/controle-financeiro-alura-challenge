package br.com.alura.controlefinanceiro.service;

import br.com.alura.controlefinanceiro.dto.DespesaDto;
import br.com.alura.controlefinanceiro.model.Despesa;

import java.util.List;

public interface DespesaService {
    Despesa save(DespesaDto despesaDto);
    List<Despesa> findAllByDescricao(String descricao);
    List<DespesaDto> findAllByAnoMes(Integer ano, Integer mes);
    Despesa findById(Long id);
    Despesa update(Despesa despesa, Long id);
    void delete(Long id);
}
