package br.com.cesario.apiminhasfinancas.models.JpaRepository;

import br.com.cesario.apiminhasfinancas.models.entidades.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
