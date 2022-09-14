package br.com.alura.controlefinanceiro.service.impl;

import br.com.alura.controlefinanceiro.dto.DespesaDto;
import br.com.alura.controlefinanceiro.exceptions.ValidacaoException;
import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.repository.DespesaRepository;
import br.com.alura.controlefinanceiro.service.DespesaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DespesaServiceImpl implements DespesaService {
    final DespesaRepository despesaRepository;

    public DespesaServiceImpl(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    @Override
    @Transactional
    public Despesa save(DespesaDto despesaDto) {

        Despesa despesa = new Despesa();
        despesa.setData(despesaDto.getData());
        despesa.setDescricao(despesaDto.getDescricao());
        despesa.setValor(despesaDto.getValor());
        if(despesaDto.getCategoria() == null) {
            despesa.setCategoria(Despesa.categoria.Outras);
        } else {
            despesa.setCategoria(despesaDto.getCategoria());
        }

        List<Despesa> listaDespesaExistente = despesaRepository.findByDescricao(despesa.getDescricao());

        if(listaDespesaExistente != null) {
            listaDespesaExistente.forEach(r -> {
                if(r.equals(despesa)) {
                    throw new ValidacaoException("Despesa duplicada");
                }
            });
        }
        return despesaRepository.save(despesa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Despesa> findAllByDescricao(String descricao) {
        if(descricao == null) {
            return despesaRepository.findAll();
        } else {
            return despesaRepository.findByDescricaoContaining(descricao);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Despesa findById(Long id) {
        return despesaRepository.findById(id).orElseThrow(
                () -> new ValidacaoException("Despesa inexistente"));
    }

    @Override
    @Transactional
    public Despesa update(Despesa despesa, Long id) {
        despesaRepository.findById(id).orElseThrow(
                () -> new ValidacaoException("Despesa inexistente"));

        despesa.setId(id);

        return despesaRepository.save(despesa);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        despesaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DespesaDto> findAllByAnoMes(Integer ano, Integer mes) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = LocalDate.of(ano, mes+1, 1).minusDays(1);

        List<Despesa> despesaList = despesaRepository.findAllByDataBetween(dataInicio, dataFim);
        List<DespesaDto> despesaDtoList = new ArrayList<>();
        despesaList.forEach(despesa -> {
            DespesaDto despesaDto = new DespesaDto(despesa.getDescricao(), despesa.getValor(), despesa.getData(), despesa.getCategoria());
            despesaDtoList.add(despesaDto);
        });

        return despesaDtoList;
    }
}
