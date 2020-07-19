package br.com.cesario.apiminhasfinancas.services.implementations;

import br.com.cesario.apiminhasfinancas.excepions.ErroAutenticacaoException;
import br.com.cesario.apiminhasfinancas.excepions.RegraNegocioExcepcion;
import br.com.cesario.apiminhasfinancas.models.JpaRepository.UsuarioRepository;
import br.com.cesario.apiminhasfinancas.models.entidades.Usuario;
import br.com.cesario.apiminhasfinancas.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioServiceImplementation implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImplementation(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if(usuarioOptional.isPresent()){
            throw new ErroAutenticacaoException("Usuario não encontrado para o email informado");
        }
        if (usuarioOptional.get().getSenha().equals(senha)){
            throw new ErroAutenticacaoException("Senha Invalida");
        }

        return usuarioOptional.get();
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = usuarioRepository.existsByEmail(email);
            if (existe){
                throw new RegraNegocioExcepcion("Já existe um usuario cadastrado com este email");
            }
        }
    }


