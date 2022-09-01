package br.com.alura.controlefinanceiro.dto;

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
public class ReceitaDto {
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
        ReceitaDto that = (ReceitaDto) o;
        return Objects.equals(descricao, that.descricao) && Objects.equals(valor, that.valor) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, valor, data);
    }
}
