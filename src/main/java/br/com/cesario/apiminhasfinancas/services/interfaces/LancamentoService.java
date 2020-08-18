package br.com.cesario.apiminhasfinancas.services.interfaces;

import br.com.cesario.apiminhasfinancas.enums.StatusLancamento;
import br.com.cesario.apiminhasfinancas.models.entidades.Lancamento;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);

    Lancamento atualizar(Lancamento lancamento);

    void deletar(Lancamento lancamento);

    List<Lancamento> buscar(Lancamento lancamentoFiltro);

    void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento);

    void validar(Lancamento lancamento);

    Optional<Lancamento> getLancamentoById(long id);

    BigDecimal obterSaldoPorUsuario(Long id);
}
