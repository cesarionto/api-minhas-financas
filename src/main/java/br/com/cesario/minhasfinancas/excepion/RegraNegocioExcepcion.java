package br.com.cesario.minhasfinancas.excepion;

public class RegraNegocioExcepcion extends RuntimeException {
    public RegraNegocioExcepcion(String mensagen) {
        super(mensagen);
    }
}
