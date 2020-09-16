package br.com.cesario.apiminhasfinancas.repository;

import br.com.cesario.apiminhasfinancas.models.entidades.Usuario;
import br.com.cesario.apiminhasfinancas.models.repositories.UsuarioRepository;
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
        Usuario usuario = new Usuario();
        usuario.setEmail("cesario@cesario.com");
        usuarioRepository.save(usuario);
        //ação
        boolean resultado = usuarioRepository.existsByEmail("cesario@gmail.com");
        //verificação
        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNãoHouverUsuarioCadastradoComOEmail(){
        usuarioRepository.deleteAll();
        boolean resultado = usuarioRepository.existsByEmail("cesario@gmail.com");
        Assertions.assertThat(resultado).isFalse();
    }


}
