package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Receita;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @PostMapping
    public ResponseEntity<Receita> teste(@RequestBody Receita receita){
        System.out.println(receita);
        return ResponseEntity.ok(receita);
    }
}
