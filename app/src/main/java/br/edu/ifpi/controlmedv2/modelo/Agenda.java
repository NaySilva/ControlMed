package br.edu.ifpi.controlmedv2.modelo;

import br.edu.ifpi.controlmedv2.enums.TipoDeCompromissoEnum;
import br.edu.ifpi.controlmedv2.modelo.Data;
import br.edu.ifpi.controlmedv2.modelo.Horario;

/**
 * Created by Eva on 24/03/2016.
 */
public class Agenda {
    private int id;
    private TipoDeCompromissoEnum tipo;
    private String data;
    private String hora;
    private boolean realizada;
    private int idCompromisso;

    public Agenda(String data, String hora){
        this.realizada = false;
        this.data = data;
        this.hora = hora;
    }

    public Agenda(){
    }

    @Override
    public String toString() {
        String str = hora + " - " + tipo.toString();
        return str;
    }

    public String text1(){
        String str = this.data + " - " + this.hora + ": " + this.tipo.toString();
        return str;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoDeCompromissoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeCompromissoEnum tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public int getIdCompromisso() {
        return idCompromisso;
    }

    public void setIdCompromisso(int idCompromisso) {
        this.idCompromisso = idCompromisso;
    }

}
