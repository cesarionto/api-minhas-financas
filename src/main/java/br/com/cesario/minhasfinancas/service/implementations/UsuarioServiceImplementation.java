package br.com.cesario.minhasfinancas.service.implementations;

import br.com.cesario.minhasfinancas.excepion.ErroAutenticacao;
import br.com.cesario.minhasfinancas.excepion.RegraNegocioExcepcion;
import br.com.cesario.minhasfinancas.models.JpaRepository.UsuarioRepository;
import br.com.cesario.minhasfinancas.models.entidades.Usuario;
import br.com.cesario.minhasfinancas.service.UsuarioService;
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
            throw new ErroAutenticacao("Usuario não encontrado para o email informado");
        }
        if (usuarioOptional.get().getSenha().equals(senha)){
            throw new ErroAutenticacao("Senha Invalida");
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


