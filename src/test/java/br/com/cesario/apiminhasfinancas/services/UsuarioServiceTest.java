package br.com.cesario.apiminhasfinancas.services;

import br.com.cesario.apiminhasfinancas.excepions.RegraNegocioExcepcion;
import br.com.cesario.apiminhasfinancas.models.repositories.UsuarioRepository;
import br.com.cesario.apiminhasfinancas.models.entidades.Usuario;
import br.com.cesario.apiminhasfinancas.services.interfaces.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void deveValidarEmail(){
        //cenario
        usuarioRepository.deleteAll();
        //acao
        usuarioService.validarEmail("cesario@email.com");

    }

    @Test
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado(){
        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@usuario.com");
        usuarioRepository.save(usuario);

        Exception exception = assertThrows(RegraNegocioExcepcion.class, () -> {
            usuarioService.validarEmail("cesario@cesario.com");
        });


    }
}
