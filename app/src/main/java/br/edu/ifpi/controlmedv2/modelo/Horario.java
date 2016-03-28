package br.edu.ifpi.controlmedv2.modelo;

import java.util.Calendar;

/**
 * Created by Eva on 24/03/2016.
 */
public class Horario {

    private int hora;
    private int minuntos;
    private int segundos;

    public int horaAtual(){
        Calendar cal = Calendar.getInstance();
        hora = cal.get(Calendar.HOUR_OF_DAY);
        return hora;
    }



}
