package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Receita;
import br.com.alura.controlefinanceiro.service.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @Operation(description = "Cadastro de receita")
    @PostMapping
    public ResponseEntity<Receita> save(@RequestBody @Valid Receita receita){
        return ResponseEntity.ok(receitaService.save(receita));
    }

    @Operation(description = "Listagem de receitas")
    @GetMapping
    public ResponseEntity<List<Receita>> findAll() {
        return ResponseEntity.ok(receitaService.findAll());
    }

    @Operation(description = "Detalhamento de receita")
    @GetMapping("/{id}")
    public ResponseEntity<Receita> findById(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.findById(id));
    }

    @Operation(description = "Atualização de receita")
    @PutMapping("/{id}")
    public ResponseEntity<Receita> update(@RequestBody @Valid Receita receita, @PathVariable Long id) {
        return ResponseEntity.ok(receitaService.update(receita, id));
    }

    @Operation(description = "Exclusão de receita")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        receitaService.delete(id);
    }
}
