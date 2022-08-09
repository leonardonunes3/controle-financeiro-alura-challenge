package br.com.alura.controlefinanceiro.service;

import br.com.alura.controlefinanceiro.model.Receita;

import java.util.List;

public interface ReceitaService {
    Receita save(Receita receita);
    List<Receita> findAll();
    Receita findById(Long id);
    Receita update(Receita receita, Long id);
    void delete(Long id);
}
