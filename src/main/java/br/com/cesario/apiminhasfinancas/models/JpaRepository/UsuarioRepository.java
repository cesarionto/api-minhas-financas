package br.com.cesario.apiminhasfinancas.models.JpaRepository;

import br.com.cesario.apiminhasfinancas.models.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}
