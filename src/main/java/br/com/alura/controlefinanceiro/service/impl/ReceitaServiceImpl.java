package br.com.alura.controlefinanceiro.service.impl;

import br.com.alura.controlefinanceiro.model.Receita;
import br.com.alura.controlefinanceiro.repository.ReceitaRepository;
import br.com.alura.controlefinanceiro.service.ReceitaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReceitaServiceImpl implements ReceitaService {
    final ReceitaRepository receitaRepository;

    public ReceitaServiceImpl(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @Override
    @Transactional
    public Receita save(Receita receita) {
        return receitaRepository.save(receita);
    }
}
