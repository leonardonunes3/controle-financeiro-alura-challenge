package br.com.alura.controlefinanceiro.service.impl;

import br.com.alura.controlefinanceiro.dto.DespesaDto;
import br.com.alura.controlefinanceiro.dto.ReceitaDto;
import br.com.alura.controlefinanceiro.dto.ResumoDto;
import br.com.alura.controlefinanceiro.service.DespesaService;
import br.com.alura.controlefinanceiro.service.ReceitaService;
import br.com.alura.controlefinanceiro.service.ResumoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ResumoServiceImpl implements ResumoService {

    final DespesaService despesaService;
    final ReceitaService receitaService;

    public ResumoServiceImpl(DespesaService despesaService, ReceitaService receitaService) {
        this.despesaService = despesaService;
        this.receitaService = receitaService;
    }

    @Override
    public ResumoDto calculaResumo(Integer ano, Integer mes) {
        List<ReceitaDto> receitaDtoList = receitaService.findAllByAnoMes(ano, mes);
        List<DespesaDto> despesaDtoList = despesaService.findAllByAnoMes(ano, mes);
        ResumoDto resumoDto = new ResumoDto();

        System.out.println(resumoDto.getValorDespesasCategorias().values());
        System.out.println(resumoDto.getValorDespesasCategorias().keySet());

        resumoDto.setValorReceitas(receitaDtoList
                .stream()
                .map(ReceitaDto::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        despesaDtoList.forEach(despesaDto -> {
            BigDecimal valor = resumoDto.getValorDespesasCategorias().get(despesaDto.getCategoria());
            if(valor == null) {
                valor = BigDecimal.ZERO;
            }
            resumoDto.getValorDespesasCategorias()
                    .put(despesaDto.getCategoria().name(), valor.add(despesaDto.getValor()));
        });
        resumoDto.setValorDespesas(despesaDtoList
                .stream()
                .map(DespesaDto::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        resumoDto.setSaldo(resumoDto.getValorReceitas().subtract(resumoDto.getValorDespesas()));

        return resumoDto;
    }


}
