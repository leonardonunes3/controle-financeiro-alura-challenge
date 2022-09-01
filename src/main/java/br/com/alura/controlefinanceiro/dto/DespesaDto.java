package br.com.alura.controlefinanceiro.dto;

import br.com.alura.controlefinanceiro.model.Despesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DespesaDto {

    @NotNull(message = "Descrição não pode ser nula")
    @NotEmpty(message = "Descrição não pode ser vazia")
    private String descricao;

    @NotNull(message = "Valor não pode ser nulo")
    private BigDecimal valor;

    @NotNull(message = "Data não pode ser nula")
    private LocalDate data;

    private Despesa.categoria categoria;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DespesaDto that = (DespesaDto) o;
        return Objects.equals(descricao, that.descricao) && Objects.equals(valor, that.valor) && Objects.equals(data, that.data) && categoria == that.categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, valor, data, categoria);
    }
}
