package br.edu.ifpi.controlmedv2.enums;

/**
 * Created by Nayara on 24/03/2016.
 */
public enum TipoDeCompromissoEnum {
    CONSULTA(1), EXAME(2), MEDICAMENTO(3);

    public int valor;

    private TipoDeCompromissoEnum(int valor) {
        valor = valor;
    }

    public static TipoDeCompromissoEnum fromInteger(int i){
        if (i == 1){
            return TipoDeCompromissoEnum.CONSULTA;
        }else if (i == 2){
            return TipoDeCompromissoEnum.EXAME;
        }else{
            return TipoDeCompromissoEnum.MEDICAMENTO;
        }
    }
}
