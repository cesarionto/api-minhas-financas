package br.com.cesario.minhasfinancas.repositorys;

import br.com.cesario.minhasfinancas.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
