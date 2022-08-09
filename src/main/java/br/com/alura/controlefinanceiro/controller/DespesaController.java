package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Despesa;
import br.com.alura.controlefinanceiro.service.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @Operation(description = "Listagem de despesas")
    @GetMapping
    public ResponseEntity<List<Despesa>> findAll() {
        return ResponseEntity.ok(despesaService.findAll());
    }

    @Operation(description = "Detalhamento de despesa")
    @GetMapping("/{id}")
    public ResponseEntity<Despesa> findById(@PathVariable Long id) {
        return ResponseEntity.ok(despesaService.findById(id));
    }

    @Operation(description = "Atualização de despesa")
    @PutMapping("/{id}")
    public ResponseEntity<Despesa> update(@RequestBody @Valid Despesa despesa, @PathVariable Long id) {
        return ResponseEntity.ok(despesaService.update(despesa, id));
    }

    @Operation(description = "Exclusão de despesa")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        despesaService.delete(id);
    }

}
