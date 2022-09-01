package br.com.alura.controlefinanceiro.service;

import br.com.alura.controlefinanceiro.dto.ReceitaDto;
import br.com.alura.controlefinanceiro.model.Receita;

import java.util.List;

public interface ReceitaService {
    Receita save(ReceitaDto receitaDto);
    Receita findById(Long id);
    List<Receita> findAllByDescricao(String descricao);
    Receita update(Receita receita, Long id);
    void delete(Long id);
    List<ReceitaDto> findAllByAnoMes(Integer ano, Integer mes);
}
