package br.com.alura.controlefinanceiro.repository;

import br.com.alura.controlefinanceiro.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByDescricao(String descricao);
    List<Receita> findByDescricaoContaining(String descricao);
    List<Receita> findAllByDataBetween(LocalDate dataInicio, LocalDate dataFim);
}
