package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.repository.DespesaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    final DespesaRepository despesaRepository;

    public DespesaController(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    @PostMapping
    public ResponseEntity<Despesa> save(@RequestBody @Valid Despesa despesa){
        System.out.println(despesa);
        return ResponseEntity.ok(despesaRepository.save(despesa));
    }

}
