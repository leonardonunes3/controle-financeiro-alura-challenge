package br.com.alura.controlefinanceiro.service.impl;

import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.repository.DespesaRepository;
import br.com.alura.controlefinanceiro.service.DespesaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DespesaServiceImpl implements DespesaService {
    final DespesaRepository despesaRepository;

    public DespesaServiceImpl(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    @Override
    @Transactional
    public Despesa save(Despesa despesa) {
        return despesaRepository.save(despesa);
    }
}
