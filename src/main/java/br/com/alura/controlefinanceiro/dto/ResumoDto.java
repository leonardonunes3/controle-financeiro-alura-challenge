package br.com.alura.controlefinanceiro.dto;

import br.com.alura.controlefinanceiro.model.Despesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
public class ResumoDto {

    public ResumoDto() {
        valorDespesasCategorias = new HashMap<>();
        for(Despesa.categoria categoria : Despesa.categoria.values()) {
            valorDespesasCategorias.put(categoria.name(), BigDecimal.ZERO);
        }
    }

    private BigDecimal valorReceitas;

    private BigDecimal valorDespesas;

    private BigDecimal saldo;
    private HashMap<String, BigDecimal> valorDespesasCategorias;

}
