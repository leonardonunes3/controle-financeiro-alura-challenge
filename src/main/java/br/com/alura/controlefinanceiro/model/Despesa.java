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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Despesa {

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

}
