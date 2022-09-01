package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.dto.DespesaDto;
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
    public ResponseEntity<Despesa> save(@RequestBody @Valid DespesaDto despesaDto){
        return ResponseEntity.ok(despesaService.save(despesaDto));
    }

    @Operation(description = "Listagem de despesas")
    @GetMapping
    public ResponseEntity<List<Despesa>> findAll(@RequestParam(required = false, value = "descricao") String descricao) {
        return ResponseEntity.ok(despesaService.findAllByDescricao(descricao));
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

    @Operation(description = "Listagem de despesas em um mês")
    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<List<DespesaDto>> findAllByAnoMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        return ResponseEntity.ok(despesaService.findAllByAnoMes(ano, mes));
    }
}
