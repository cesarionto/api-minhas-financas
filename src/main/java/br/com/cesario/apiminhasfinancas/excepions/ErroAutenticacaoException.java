package br.com.cesario.apiminhasfinancas.excepions;

public class ErroAutenticacaoException extends RuntimeException {
    public ErroAutenticacaoException(String mensagem){
        super(mensagem);
    }
}
