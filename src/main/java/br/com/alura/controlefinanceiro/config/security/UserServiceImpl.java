package br.com.alura.controlefinanceiro.config.security;

import br.com.alura.controlefinanceiro.model.Cliente;
import br.com.alura.controlefinanceiro.repository.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserDetailsService {

    final ClienteRepository clienteRepository;

    public UserServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cliente = clienteRepository.findByEmail(email);
        if (Objects.isNull(cliente)) {
            throw new UsernameNotFoundException(email);
        }

        return new UserDetailsImpl(cliente);
    }
}
