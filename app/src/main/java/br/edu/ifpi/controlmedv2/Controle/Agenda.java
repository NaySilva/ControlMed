package br.edu.ifpi.controlmedv2.Controle;

import br.edu.ifpi.controlmedv2.enums.TipoDeCompromissoEnum;
import br.edu.ifpi.controlmedv2.modelo.Data;
import br.edu.ifpi.controlmedv2.modelo.Horario;

/**
 * Created by Eva on 24/03/2016.
 */
public class Agenda {
    private TipoDeCompromissoEnum tipo;
    private Data data;
    private Horario hora;
    private boolean realizada;

    public Agenda(){
        this.realizada = false;
    }

}
