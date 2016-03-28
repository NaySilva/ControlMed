package br.edu.ifpi.controlmedv2.enums;

/**
 * Created by Eva on 24/03/2016.
 */
public enum UnidadeDeMedicamentoEnum {

    AMPOLA(1), CAPSULAS(2), COPOS(3),GOTAS(4),GRAMAS(5),INJEÇÃES(6), PILULAS(7), MILIGRAMAS(8), OUTROS(0);

    public int valor;

    private UnidadeDeMedicamentoEnum(int valor) {
        valor = valor;
    }

}
