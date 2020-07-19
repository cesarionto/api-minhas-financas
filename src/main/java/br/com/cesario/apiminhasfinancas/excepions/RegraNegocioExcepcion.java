package br.com.cesario.apiminhasfinancas.excepions;

public class RegraNegocioExcepcion extends RuntimeException {
    public RegraNegocioExcepcion(String mensagen) {
        super(mensagen);
    }
}
