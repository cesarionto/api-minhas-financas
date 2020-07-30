package br.com.cesario.apiminhasfinancas.controllers;

import br.com.cesario.apiminhasfinancas.dto.LancamentoDTO;
import br.com.cesario.apiminhasfinancas.enums.StatusLancamento;
import br.com.cesario.apiminhasfinancas.enums.TipoLancamento;
import br.com.cesario.apiminhasfinancas.excepions.RegraNegocioExcepcion;
import br.com.cesario.apiminhasfinancas.models.entidades.Lancamento;
import br.com.cesario.apiminhasfinancas.models.entidades.Usuario;
import br.com.cesario.apiminhasfinancas.services.LancamentoService;
import br.com.cesario.apiminhasfinancas.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {
    private LancamentoService lancamentoService;
    private UsuarioService usuarioService;

    public LancamentoController(LancamentoService lancamentoService, UsuarioService usuarioService){
        this.lancamentoService = lancamentoService;
        this.usuarioService = usuarioService;

    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoDTO lancamentoDTO){

    }

    private Lancamento converter (LancamentoDTO lancamentoDTO){
        Lancamento lancamento = new Lancamento();
        lancamento.setId(lancamentoDTO.getId());
        lancamento.setDescricao(lancamentoDTO.getDescricao());
        lancamento.setAno(lancamentoDTO.getAno());
        lancamento.setAno(lancamentoDTO.getAno());
        lancamento.setValor(lancamentoDTO.getValor());
        Usuario usuario = usuarioService.getUsuarioById(lancamentoDTO.getUsuario())
                .orElse(()-> new RegraNegocioExcepcion("Usuário não encontrado"));
        lancamento.setUsuario(usuario);
        lancamento.setTipo(TipoLancamento.valueOf(lancamentoDTO.getTipo()));
        return lancamento;
    }
}
