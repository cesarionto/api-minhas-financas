package br.com.cesario.minhasfinancas.models.JpaRepository;

import br.com.cesario.minhasfinancas.models.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
