package br.com.cesario.apiminhasfinancas.services;

import br.com.cesario.apiminhasfinancas.models.entidades.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario autenticar (String email, String senha);

    Usuario salvarUsuario (Usuario usuario);

    void validarEmail(String email);

    Optional<Usuario> getUsuarioById(long id);

}
