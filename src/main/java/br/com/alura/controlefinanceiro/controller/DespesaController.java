package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.service.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }


    @Operation(description = "Cadastro de despesas")
    @PostMapping
    public ResponseEntity<Despesa> save(@RequestBody @Valid Despesa despesa){
        return ResponseEntity.ok(despesaService.save(despesa));
    }

}
