package br.com.cesario.minhasfinancas.models.JpaRepository;

import br.com.cesario.minhasfinancas.models.entidades.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
