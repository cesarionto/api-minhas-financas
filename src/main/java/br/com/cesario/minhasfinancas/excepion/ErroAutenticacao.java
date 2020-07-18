package br.com.cesario.minhasfinancas.excepion;

public class ErroAutenticacao extends RuntimeException {
    public ErroAutenticacao(String mensagem){
        super(mensagem);
    }
}
