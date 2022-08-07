package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Despesa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @PostMapping
    public ResponseEntity<Despesa> teste(@RequestBody Despesa despesa){
        System.out.println(despesa);
        return ResponseEntity.ok(despesa);
    }

}
