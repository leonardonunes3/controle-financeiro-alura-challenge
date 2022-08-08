package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Receita;
import br.com.alura.controlefinanceiro.repository.ReceitaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    final ReceitaRepository receitaRepository;

    public ReceitaController(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @PostMapping
    public ResponseEntity<Receita> save(@RequestBody @Valid Receita receita){
        System.out.println(receita);
        return ResponseEntity.ok(receitaRepository.save(receita));
    }
}
