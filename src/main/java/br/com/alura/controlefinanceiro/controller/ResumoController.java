package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.dto.ReceitaDto;
import br.com.alura.controlefinanceiro.dto.ResumoDto;
import br.com.alura.controlefinanceiro.service.ResumoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/resumo")
public class ResumoController {

    final ResumoService resumoService;

    public ResumoController(ResumoService resumoService) {
        this.resumoService = resumoService;
    }

    @Operation(description = "Resumo de um mês")
    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<ResumoDto> buscaResumo(@PathVariable Integer ano, @PathVariable Integer mes) {
        return ResponseEntity.ok(resumoService.calculaResumo(ano, mes));
    }
}
