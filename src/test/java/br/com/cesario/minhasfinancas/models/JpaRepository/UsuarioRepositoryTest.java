package br.com.cesario.minhasfinancas.models.JpaRepository;

import br.com.cesario.minhasfinancas.models.entidades.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void deveVerificarAExisteciaDeUmEmail(){
        //cenario
        Usuario usuario = Usuario.builder().nome("Cesário").email("cesario@gmail.com").senha("789456").build();
        usuarioRepository.save(usuario);

        //ação
        boolean resultado = usuarioRepository.existsByEmail("cesario@gmail.com");

        //verificação
        Assertions.assertThat(resultado).isTrue();
    }


}
