package br.edu.ifpi.controlmedv2.enums;

/**
 * Created by Eva on 24/03/2016.
 */
public enum MedidaDeTempoEnum {
    DIA(1),SEMANA(2),MESES(3),SEMESTRE(4),ANO(5),SEMPRE(5);

    public int valor;

    private MedidaDeTempoEnum(int valor) {
        valor = valor;
    }

}
