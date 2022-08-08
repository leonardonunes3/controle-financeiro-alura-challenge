package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Receita;
import br.com.alura.controlefinanceiro.service.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @Operation(description = "Cadastro de receitas")
    @PostMapping
    public ResponseEntity<Receita> save(@RequestBody @Valid Receita receita){
        return ResponseEntity.ok(receitaService.save(receita));
    }

}
