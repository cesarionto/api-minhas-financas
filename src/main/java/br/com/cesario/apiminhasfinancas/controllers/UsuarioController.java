package br.com.cesario.apiminhasfinancas.controllers;

import br.com.cesario.apiminhasfinancas.dto.UsuarioDTO;
import br.com.cesario.apiminhasfinancas.dto.UsuarioLogadoDTO;
import br.com.cesario.apiminhasfinancas.excepions.ErroAutenticacaoException;
import br.com.cesario.apiminhasfinancas.excepions.RegraNegocioExcepcion;
import br.com.cesario.apiminhasfinancas.models.entidades.Usuario;
import br.com.cesario.apiminhasfinancas.models.repositories.UsuarioRepository;
import br.com.cesario.apiminhasfinancas.services.interfaces.LancamentoService;
import br.com.cesario.apiminhasfinancas.services.interfaces.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;
    private LancamentoService lancamentoService;

    public UsuarioController(UsuarioService usuarioService, LancamentoService lancamentoService){
        this.usuarioService = usuarioService;
        this.lancamentoService = lancamentoService;
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO usuarioDTO){
        try {
            Usuario usuario = usuarioService.autenticar(usuarioDTO.getEmail(), usuarioDTO.getSenha());
            UsuarioLogadoDTO dados = new UsuarioLogadoDTO();
            dados.setId(usuario.getId());
            dados.setNome(usuario.getNome());
            return ResponseEntity.ok(dados);
        }catch (ErroAutenticacaoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        try {
            return new ResponseEntity(usuarioService.salvarUsuario(usuario), HttpStatus.CREATED);
        }catch (RegraNegocioExcepcion e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("{id}/saldo")
    public  ResponseEntity obterSaldo(@PathVariable("id") Long id){
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        if (!usuario.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
        return ResponseEntity.ok(saldo);

    }
}
