package br.com.cesario.apiminhasfinancas.controllers;

import br.com.cesario.apiminhasfinancas.dto.AtualizaStatusDTO;
import br.com.cesario.apiminhasfinancas.dto.LancamentoDTO;
import br.com.cesario.apiminhasfinancas.enums.StatusLancamento;
import br.com.cesario.apiminhasfinancas.enums.TipoLancamento;
import br.com.cesario.apiminhasfinancas.excepions.RegraNegocioExcepcion;
import br.com.cesario.apiminhasfinancas.models.entidades.Lancamento;
import br.com.cesario.apiminhasfinancas.models.entidades.Usuario;
import br.com.cesario.apiminhasfinancas.services.interfaces.LancamentoService;
import br.com.cesario.apiminhasfinancas.services.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lancamentos")
@RequiredArgsConstructor
public class LancamentoController {

    private final LancamentoService lancamentoService;
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoDTO lancamentoDTO) {
        try {
            Lancamento lancamento = converter(lancamentoDTO);
            lancamento = lancamentoService.salvar(lancamento);
            return new ResponseEntity(lancamento, HttpStatus.CREATED);
        } catch (RegraNegocioExcepcion e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") long id, @RequestBody LancamentoDTO lancamentoDTO) {
        return lancamentoService.getLancamentoById(id).map(entity -> {
            try {
                Lancamento lancamento = converter(lancamentoDTO);
                lancamento.setId(entity.getId());
                lancamentoService.atualizar(lancamento);
                return ResponseEntity.ok(lancamento);
            } catch (RegraNegocioExcepcion e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity("Lancamento não encontrado", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") long id) {
        return lancamentoService.getLancamentoById(id).map(entity -> {
            lancamentoService.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity("Lançamento não encontrado.", HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public ResponseEntity buscar(
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "mes", required = false) Integer mes,
            @RequestParam(value = "ano", required = false) Integer ano,
            @RequestParam("usuario") Long idUsuario) {

        Lancamento lancamentoFiltro = new Lancamento();
        lancamentoFiltro.setDescricao(descricao);
        lancamentoFiltro.setMes(mes);
        lancamentoFiltro.setAno(ano);

        Optional<Usuario> usuario = usuarioService.getUsuarioById(idUsuario);
        if (!usuario.isPresent()) {
            return ResponseEntity.badRequest().body("Não  foi possivel realizar a consulta." +
                    " Usuario não encontrado.");
        } else {
            lancamentoFiltro.setUsuario(usuario.get());
        }
        List<Lancamento> lancamentos = lancamentoService.buscar(lancamentoFiltro);
        return ResponseEntity.ok(lancamentos);
    }

    @PutMapping("{id}/atualiza-status")
    public ResponseEntity atualizarStatus(@PathVariable long id, @RequestBody AtualizaStatusDTO atualizaStatusDTO) {
        return lancamentoService.getLancamentoById(id).map(entity -> {
            StatusLancamento statusLancamento = StatusLancamento.valueOf(atualizaStatusDTO.getStatus());
            if (statusLancamento == null) {
                return ResponseEntity.badRequest().body("Não Foi possivel atualizar o status");
            }
            try {
                entity.setStatus(statusLancamento);
                lancamentoService.atualizar(entity);
                return ResponseEntity.ok(entity);
            } catch (RegraNegocioExcepcion e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() ->
                new ResponseEntity("Lancamento não encontrado na base de dados", HttpStatus.BAD_REQUEST)
        );
    }



    private Lancamento converter(LancamentoDTO lancamentoDTO) {
        Lancamento lancamento = new Lancamento();
        lancamento.setId(lancamentoDTO.getId());
        lancamento.setDescricao(lancamentoDTO.getDescricao());
        lancamento.setMes(lancamentoDTO.getMes());
        lancamento.setAno(lancamentoDTO.getAno());
        lancamento.setValor(lancamentoDTO.getValor());
        Usuario usuario = usuarioService.getUsuarioById(lancamentoDTO.getUsuario())
                .orElseThrow(() -> new RegraNegocioExcepcion("Usuário não encontrado"));
        lancamento.setUsuario(usuario);

        if (lancamentoDTO.getTipo() != null) {
            lancamento.setTipo(TipoLancamento.valueOf(lancamentoDTO.getTipo()));
        }

        if (lancamentoDTO.getStatus() != null) {
            lancamento.setStatus(StatusLancamento.valueOf(lancamentoDTO.getStatus()));
        }
        return lancamento;
    }
}
