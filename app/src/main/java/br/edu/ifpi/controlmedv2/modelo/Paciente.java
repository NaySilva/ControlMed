package br.edu.ifpi.controlmedv2.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nayara on 23/03/16.
 */
public class Paciente {

    private int id;
    private String nome;
    private boolean principal;
    private List<Compromisso> compromissos;
    private int qtd = 0;


    public Paciente(String nome) {
        this.principal = false;
        this.nome = nome;
        compromissos = new ArrayList<>();
    }

    public void addCompromisso(Compromisso compromisso){
        compromissos.add(compromisso);
        qtd++;
    }

    @Override
    public String toString() {
        String str = this.getNome();
        return str;
    }



    public int getQtd(){
        return qtd;
    }

    public void setPrincipal(boolean res) {
        this.principal = res;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }
}
