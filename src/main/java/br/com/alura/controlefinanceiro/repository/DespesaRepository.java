package br.com.alura.controlefinanceiro.repository;

import br.com.alura.controlefinanceiro.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByDescricao(String descricao);
    List<Despesa> findByDescricaoContaining(String descricao);
    List<Despesa> findAllByDataBetween(LocalDate dataInicio, LocalDate dataFim);
}
