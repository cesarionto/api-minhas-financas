package br.com.cesario.minhasfinancas.service;

import br.com.cesario.minhasfinancas.excepion.RegraNegocioExcepcion;
import br.com.cesario.minhasfinancas.models.JpaRepository.UsuarioRepository;
import br.com.cesario.minhasfinancas.models.entidades.Usuario;
import org.junit.jupiter.api.Assertions;
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
        Usuario usuario = Usuario.builder().email("cesario@cesario.com").build();
        usuarioRepository.save(usuario);

        Exception exception = assertThrows(RegraNegocioExcepcion.class, () -> {
            usuarioService.validarEmail("cesario@cesario.com");
        });


    }
}
