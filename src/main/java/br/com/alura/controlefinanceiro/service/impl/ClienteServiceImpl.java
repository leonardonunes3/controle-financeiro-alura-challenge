package br.com.alura.controlefinanceiro.service.impl;

import br.com.alura.controlefinanceiro.model.Cliente;
import br.com.alura.controlefinanceiro.repository.ClienteRepository;
import br.com.alura.controlefinanceiro.service.ClienteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    final PasswordEncoder passwordEncoder;
    final ClienteRepository clienteRepository;

    public ClienteServiceImpl(PasswordEncoder passwordEncoder, ClienteRepository clienteRepository) {
        this.passwordEncoder = passwordEncoder;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        if (Objects.isNull(cliente.getId())) {
            cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        }
        return clienteRepository.save(cliente);
    }
}
