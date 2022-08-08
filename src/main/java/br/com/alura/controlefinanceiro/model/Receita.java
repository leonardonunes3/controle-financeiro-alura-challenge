package br.com.alura.controlefinanceiro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Descrição não pode ser nula")
    @NotEmpty(message = "Descrição não pode ser vazia")
    private String descricao;

    @NotNull(message = "Valor não pode ser nulo")
    private BigDecimal valor;

    @NotNull(message = "Data não pode ser nula")
    private LocalDate data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receita receita = (Receita) o;
        return Objects.equals(descricao, receita.descricao) && Objects.equals(data, receita.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, data);
    }
}
