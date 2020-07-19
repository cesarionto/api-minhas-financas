package br.com.cesario.apiminhasfinancas.service;

import br.com.cesario.apiminhasfinancas.models.entidades.Usuario;

public interface UsuarioService {

    Usuario autenticar (String email, String senha);

    Usuario salvarUsuario (Usuario usuario);

    void validarEmail(String email) ;

}
