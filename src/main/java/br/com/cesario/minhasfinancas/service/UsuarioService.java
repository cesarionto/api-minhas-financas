package br.com.cesario.minhasfinancas.service;

import br.com.cesario.minhasfinancas.models.entidades.Usuario;

public interface UsuarioService {

    Usuario autenticar (String email, String senha);

    Usuario salvarUsuario (Usuario usuario);

    void validarEmail(String email) ;

}
