package br.com.alura.controlefinanceiro.service.impl;

import br.com.alura.controlefinanceiro.dto.ReceitaDto;
import br.com.alura.controlefinanceiro.exceptions.ValidacaoException;
import br.com.alura.controlefinanceiro.model.Receita;
import br.com.alura.controlefinanceiro.repository.ReceitaRepository;
import br.com.alura.controlefinanceiro.service.ReceitaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceitaServiceImpl implements ReceitaService {
    final ReceitaRepository receitaRepository;

    public ReceitaServiceImpl(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @Override
    @Transactional
    public Receita save(ReceitaDto receitaDto) {

        Receita receita = new Receita();
        receita.setData(receitaDto.getData());
        receita.setDescricao(receitaDto.getDescricao());
        receita.setValor(receitaDto.getValor());

        List<Receita> listaReceitaExistente = receitaRepository.findByDescricao(receita.getDescricao());

        if(listaReceitaExistente != null) {
            listaReceitaExistente.forEach(r -> {
                if(r.equals(receita)) {
                    throw new ValidacaoException("Receita duplicada");
                }
            });
        }
        return receitaRepository.save(receita);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Receita> findAllByDescricao(String descricao) {
        if(descricao == null) {
           return receitaRepository.findAll();
        } else {
            return receitaRepository.findByDescricaoContaining(descricao);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Receita findById(Long id) {
        return receitaRepository.findById(id).orElseThrow(
                () -> new ValidacaoException("Receita inexistente"));
    }

    @Override
    @Transactional
    public Receita update(Receita receita, Long id) {
        receitaRepository.findById(id).orElseThrow(
                () -> new ValidacaoException("Receita inexistente"));

        receita.setId(id);

        return receitaRepository.save(receita);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        receitaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceitaDto> findAllByAnoMes(Integer ano, Integer mes) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = LocalDate.of(ano, mes+1, 1).minusDays(1);

        List<Receita> receitaList = receitaRepository.findAllByDataBetween(dataInicio, dataFim);
        List<ReceitaDto> receitaDtoList = new ArrayList<>();
        receitaList.forEach(receita -> {
            ReceitaDto receitaDto = new ReceitaDto(receita.getDescricao(), receita.getValor(), receita.getData());
            receitaDtoList.add(receitaDto);
        });

        return receitaDtoList;
    }
}
