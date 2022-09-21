package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.config.security.JWTAuthenticationFilter;
import br.com.alura.controlefinanceiro.dto.UserLoginDto;
import br.com.alura.controlefinanceiro.model.Cliente;
import br.com.alura.controlefinanceiro.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
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
