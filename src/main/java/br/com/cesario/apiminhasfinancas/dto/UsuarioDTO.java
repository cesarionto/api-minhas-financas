package br.com.cesario.apiminhasfinancas.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTO {
    private String email, nome, senha;
}
