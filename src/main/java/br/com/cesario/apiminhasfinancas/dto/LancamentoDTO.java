package br.com.cesario.apiminhasfinancas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoDTO {
    private long id;
    private String descricao;
    private Integer mes;
    private Integer ano;
    private long usuario;
    private BigDecimal valor;
    private String tipo;
    private String status;
}
