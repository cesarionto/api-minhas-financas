package br.com.cesario.apiminhasfinancas.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
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
