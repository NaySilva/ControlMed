package br.edu.ifpi.controlmedv2.enums;

/**
 * Created by Eva on 24/03/2016.
 */
public enum TipoDeCompromissoEnum {
    CONSULTA(1), EXAMES(2);

    public int valor;

    private TipoDeCompromissoEnum(int valor) {
        valor = valor;
    }
}
