package br.com.cesario.minhasfinancas.service.impl;

import br.com.cesario.minhasfinancas.models.JpaRepository.UsuarioRepository;
import br.com.cesario.minhasfinancas.models.entidades.Usuario;
import br.com.cesario.minhasfinancas.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {


    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void validarEmail(String email) {

    }

}
