package br.com.cesario.apiminhasfinancas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLogadoDTO {
    private Long id;
    private String nome;
}
