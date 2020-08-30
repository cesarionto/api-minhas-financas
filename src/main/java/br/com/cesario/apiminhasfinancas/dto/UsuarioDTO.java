package br.com.cesario.apiminhasfinancas.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {
    private String email, nome, senha;
}
