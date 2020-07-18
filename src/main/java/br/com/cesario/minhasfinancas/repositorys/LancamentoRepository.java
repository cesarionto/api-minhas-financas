package br.com.cesario.minhasfinancas.repositorys;

import br.com.cesario.minhasfinancas.models.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
