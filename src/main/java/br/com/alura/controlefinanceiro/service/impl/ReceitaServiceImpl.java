package br.com.alura.controlefinanceiro.service.impl;

import br.com.alura.controlefinanceiro.model.Receita;
import br.com.alura.controlefinanceiro.repository.ReceitaRepository;
import br.com.alura.controlefinanceiro.service.ReceitaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReceitaServiceImpl implements ReceitaService {
    final ReceitaRepository receitaRepository;

    public ReceitaServiceImpl(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @Override
    @Transactional
    public Receita save(Receita receita) {
        List<Receita> listaReceitaExistente = receitaRepository.findByDescricao(receita.getDescricao());

        if(listaReceitaExistente != null) {
            listaReceitaExistente.forEach(r -> {
                if(r.equals(receita)) {
                    throw new RuntimeException("Receita duplicada");
                }
            });
        }
        return receitaRepository.save(receita);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Receita> findAll() {
        return receitaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Receita findById(Long id) {
        return receitaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Receita inexistente"));
    }

    @Override
    @Transactional
    public Receita update(Receita receita, Long id) {
        receitaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Receita inexistente"));

        receita.setId(id);

        return save(receita);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        receitaRepository.deleteById(id);
    }

}
