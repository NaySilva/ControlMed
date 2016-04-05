package br.edu.ifpi.controlmedv2.modelo;

import java.util.Calendar;

/**
 * Created by Eva on 24/03/2016.
 */
public class Horario {

    private int hora;
    private int minuto;
    private int segundo;

    @Override
    public String toString() {
        String str = String.format("%02d", hora) + ":" + String.format("%02d", minuto);
        return str;
    }

    public String horarioAtual(){
        horaAtual();
        minutoAtual();
        return String.format("%02d", hora) + ":" + String.format("%02d",minuto);
    }

    public String horaAtual(){
        Calendar cal = Calendar.getInstance();
        hora = cal.get(Calendar.HOUR_OF_DAY);
        return String.format("%02d", hora);
    }

    public String minutoAtual(){
        Calendar cal = Calendar.getInstance();
        minuto =  cal.get(Calendar.MINUTE);
        return String.format("%02d", minuto);
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
}
