package br.com.alura.controlefinanceiro.service;

import br.com.alura.controlefinanceiro.dto.ResumoDto;

public interface ResumoService {

    ResumoDto calculaResumo(Integer ano, Integer mes);
}
