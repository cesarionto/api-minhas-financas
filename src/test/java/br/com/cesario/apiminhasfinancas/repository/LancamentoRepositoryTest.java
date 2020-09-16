package br.com.cesario.apiminhasfinancas.repository;


import br.com.cesario.apiminhasfinancas.enums.StatusLancamento;
import br.com.cesario.apiminhasfinancas.enums.TipoLancamento;
import br.com.cesario.apiminhasfinancas.models.entidades.Lancamento;
import br.com.cesario.apiminhasfinancas.models.repositories.LancamentoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void deveSalvarUmLancamento() {
        //cenario
        Lancamento lancamento = criarLancamento();

        //ação
        lancamento = lancamentoRepository.save(lancamento);

        //resultado
        //pedi para a classe asserions verificar se foi salvo na base
        Assertions.assertThat(lancamento.getId()).isNotNull();

    }

    @Test
    public void deveDeletarUmLancamento() {
        //criando cenario
        Lancamento lancamento = criarEPersistirLancamento();

        lancamento = testEntityManager.find(Lancamento.class, lancamento.getId()); //verificando se o lancamento foi realmente salvo, recebe a classe da entidade e o
        //o seu id e se existir ele retorna um lancamento

        //aplicando a acçao
        lancamentoRepository.delete(lancamento);

        //criando uma nova instancia de lancamento para ver se foi deletado.
        Lancamento lancamentoInexistente = testEntityManager.find(Lancamento.class, lancamento.getId());

        //Verificando se a instancia de lancamento é nula. caso seja, significa que o lancamento foi deletado.
        //Assertions.assertThat(lancamentoInexistente).isNull();
        Assert.assertNull(lancamentoInexistente);
    }



    @Test
    public void deveAtualizarLancamento() {
        //criando o cenario
        //para o lançamento ser atualizado ele precisa esta salvo.
        Lancamento lancamento = criarEPersistirLancamento();
        lancamento.setDescricao("Lancamento Atualizado");
        lancamento.setStatus(StatusLancamento.CANCELADO);

        //atualizando o lancamento
        lancamentoRepository.save(lancamento);

        //verificando o lancamento la na base
        Lancamento lancamentoAtualizado = testEntityManager.find(Lancamento.class, lancamento.getId());

        //Verificando se o lancamento atualizado esta com os valores do lancamento persistido ou modificados.
        Assertions.assertThat(lancamentoAtualizado.getDescricao()).isEqualTo("Lancamento Atualizado");
        Assertions.assertThat(lancamentoAtualizado.getStatus()).isEqualTo(StatusLancamento.CANCELADO);

    }

    @Test
    public void deveBuscarLancamentosPorId(){
        //criando cenario
        Lancamento lancamento = criarEPersistirLancamento();

        //retornando o lancamento salvo caso exista
        Optional<Lancamento> encontrado = lancamentoRepository.findById(lancamento.getId());

        //verificando se é presente no objeto
        Assert.assertTrue(encontrado.isPresent());
       // Assertions.assertThat(encontrado.isPresent()).isTrue();
    }

    private Lancamento criarEPersistirLancamento() {
        Lancamento lancamento = criarLancamento();
        testEntityManager.persist(lancamento); //persistindo o lancamento
        return lancamento;
    }

    private Lancamento criarLancamento() {
        Lancamento lancamento = new Lancamento();
        lancamento.setAno(2020);
        lancamento.setMes(1);
        lancamento.setDescricao("lançamento qualquer");
        lancamento.setValor(BigDecimal.valueOf(10));
        lancamento.setTipo(TipoLancamento.RECEITA);
        lancamento.setStatus(StatusLancamento.PENDENTE);
        lancamento.setDataCadastro(LocalDate.now());
        return lancamento;
    }
}
