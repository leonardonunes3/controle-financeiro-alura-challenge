package br.com.alura.controlefinanceiro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    private BigDecimal valor;
    private LocalDate data;

    private categoria categoria;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Despesa despesa = (Despesa) o;
        return Objects.equals(descricao, despesa.descricao) && Objects.equals(data.getMonth(), despesa.data.getMonth())
                && Objects.equals(data.getYear(), despesa.data.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, data);
    }

    public enum categoria {
        Alimentacao, Saude, Moradia, Transporte, Educacao, Lazer, Imprevistos, Outras;
    }
}
