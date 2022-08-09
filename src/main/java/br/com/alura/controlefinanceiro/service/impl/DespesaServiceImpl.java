package br.com.alura.controlefinanceiro.service.impl;

import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.repository.DespesaRepository;
import br.com.alura.controlefinanceiro.service.DespesaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DespesaServiceImpl implements DespesaService {
    final DespesaRepository despesaRepository;

    public DespesaServiceImpl(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    @Override
    @Transactional
    public Despesa save(Despesa despesa) {
        List<Despesa> listaDespesaExistente = despesaRepository.findByDescricao(despesa.getDescricao());

        if(listaDespesaExistente != null) {
            listaDespesaExistente.forEach(r -> {
                if(r.equals(despesa)) {
                    throw new RuntimeException("Despesa duplicada");
                }
            });
        }
        return despesaRepository.save(despesa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Despesa> findAll() {
        return despesaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Despesa findById(Long id) {
        return despesaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Despesa inexistente"));
    }

    @Override
    @Transactional
    public Despesa update(Despesa despesa, Long id) {
        despesaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Despesa inexistente"));

        despesa.setId(id);

        return save(despesa);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        despesaRepository.deleteById(id);
    }
}
