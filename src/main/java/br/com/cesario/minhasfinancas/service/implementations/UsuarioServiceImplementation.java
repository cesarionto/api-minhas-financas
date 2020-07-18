package br.com.cesario.minhasfinancas.service.implementations;

import br.com.cesario.minhasfinancas.excepion.RegraNegocioExcepcion;
import br.com.cesario.minhasfinancas.models.JpaRepository.UsuarioRepository;
import br.com.cesario.minhasfinancas.models.entidades.Usuario;
import br.com.cesario.minhasfinancas.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImplementation implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImplementation(UsuarioRepository usuarioRepository) {
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
        boolean existe = usuarioRepository.existsByEmail(email);
            if (existe){
                throw new RegraNegocioExcepcion("Já existe um usuario cadastrado com este email");
            }
        }
    }


