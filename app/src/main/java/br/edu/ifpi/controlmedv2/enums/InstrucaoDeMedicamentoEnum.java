package br.edu.ifpi.controlmedv2.enums;

/**
 * Created by Eva on 24/03/2016.
 */
public enum InstrucaoDeMedicamentoEnum {
    ANTES_DE_ALIMENTOS(1), COM_COMIDA(2), DEPOIS_DA_COMIDA(3), NENHUMA_INSTRUCAO(4);

    public int valor;

    private InstrucaoDeMedicamentoEnum(int valor) {
        valor = valor;
    }
}
