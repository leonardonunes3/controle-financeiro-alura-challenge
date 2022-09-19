package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.Cliente;
import br.com.alura.controlefinanceiro.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(description = "Cadastro de clientes")
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente cliente){
        return ResponseEntity.ok(clienteService.save(cliente));
    }
}
