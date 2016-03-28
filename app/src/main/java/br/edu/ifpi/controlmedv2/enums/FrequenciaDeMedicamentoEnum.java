package br.edu.ifpi.controlmedv2.enums;

/**
 * Created by Eva on 24/03/2016.
 */
public enum FrequenciaDeMedicamentoEnum {

    EVENTO_UNICO(1), DIARIAMENTE(2), CADA_X_DIAS(3), SEMANALMENTE(4), MENSALMENTE(5), CADA_X_HORAS(6), QUANDO_PRECISAR(0);

    public int valor;

    private FrequenciaDeMedicamentoEnum(int valor) {
        valor = valor;
    }
}
