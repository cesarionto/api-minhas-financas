package br.com.cesario.apiminhasfinancas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    private String email, nome, senha;
}
