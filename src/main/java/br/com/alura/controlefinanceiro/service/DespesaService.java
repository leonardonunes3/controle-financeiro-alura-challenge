package br.com.alura.controlefinanceiro.service;

import br.com.alura.controlefinanceiro.model.Despesa;

import java.util.List;

public interface DespesaService {
    Despesa save(Despesa despesa);
    List<Despesa> findAll();
    Despesa findById(Long id);
    Despesa update(Despesa despesa, Long id);
    void delete(Long id);
}
