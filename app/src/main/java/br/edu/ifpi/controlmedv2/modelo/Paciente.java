package br.edu.ifpi.controlmedv2.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.controlmedv2.Controle.Agenda;

/**
 * Created by aluno on 23/03/16.
 */
public class Paciente {

    private int id;
    private String nome;
    private boolean principal;
    private List<Agenda> compromissos;


    public Paciente(String nome) {
        this.principal = false;
        this.nome = nome;
        compromissos = new ArrayList<>();
    }

    public void addCompromisso(Agenda compromisso){
        compromissos.add(compromisso);
    }

    @Override
    public String toString() {
        String str = this.getNome();
        return str;
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

    public List<Agenda> getCompromissos() {
        return compromissos;
    }
}
