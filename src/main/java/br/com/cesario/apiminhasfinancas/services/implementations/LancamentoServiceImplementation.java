package br.com.cesario.apiminhasfinancas.services.implementations;

import br.com.cesario.apiminhasfinancas.enums.StatusLancamento;
import br.com.cesario.apiminhasfinancas.enums.TipoLancamento;
import br.com.cesario.apiminhasfinancas.excepions.RegraNegocioExcepcion;
import br.com.cesario.apiminhasfinancas.models.repositories.LancamentoRepository;
import br.com.cesario.apiminhasfinancas.models.entidades.Lancamento;
import br.com.cesario.apiminhasfinancas.services.interfaces.LancamentoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LancamentoServiceImplementation implements LancamentoService {

    private LancamentoRepository lancamentoRepository;

    public LancamentoServiceImplementation(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        validar(lancamento);
        lancamento.setStatus(StatusLancamento.PENDENTE);
        lancamento.setDataCadastro(LocalDate.now());
        return lancamentoRepository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        validar(lancamento);
        return lancamentoRepository.save(lancamento);
    }

    @Override
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        lancamentoRepository.delete(lancamento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        Example example = Example.of(lancamentoFiltro,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return lancamentoRepository.findAll(example);
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento) {
        lancamento.setStatus(statusLancamento);
        atualizar(lancamento);
    }

    @Override
    public void validar(Lancamento lancamento) {
        if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
            throw new RegraNegocioExcepcion("Informe uma descrição válida");
        }
        if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
            throw new RegraNegocioExcepcion("Informe um mes válido");
        }
        if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
            throw new RegraNegocioExcepcion("Informe um ano válido");
        }
        if (lancamento.getUsuario() == null) {
            throw new RegraNegocioExcepcion("Informe um usuario valido");
        }
        if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
            throw new RegraNegocioExcepcion("Informe um valor valido");
        }
        if (lancamento.getTipo() == null) {
            throw new RegraNegocioExcepcion("Informe um tipo de lançamento valido");
        }
    }

    @Override
    public Optional<Lancamento> getLancamentoById(long id) {
        return lancamentoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal obterSaldoPorUsuario(Long id) {
        BigDecimal receitas = lancamentoRepository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.RECEITA);
        BigDecimal despesas = lancamentoRepository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.DESPESA);

        if (receitas == null){
            receitas = BigDecimal.ZERO;
        }
        if (despesas == null){
            despesas = BigDecimal.ZERO;
        }

        return receitas.subtract(despesas);
    }
}
