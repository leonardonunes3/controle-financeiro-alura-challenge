package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.dto.UserLoginDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class LoginController {
    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = { "application/json" }, consumes = { "application/json" })
    @Operation(operationId = "login")
    @SecurityRequirements()
    public void login(@Parameter(name = "LoginDto", description = "Login") @Valid @RequestBody(required = true) UserLoginDto userLoginDto) {
    }
}
