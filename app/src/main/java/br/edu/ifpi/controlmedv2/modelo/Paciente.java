package br.edu.ifpi.controlmedv2.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 23/03/16.
 */
public class Paciente {

    private int id;
    private String nome;
    private boolean principal;
    private List<Medicamentos> medicamentos;


    public Paciente(String nome) {
        this.principal = false;
        this.nome = nome;
        medicamentos = new ArrayList<>();
    }

    public void addMedicamento(Medicamentos medicamento){
        medicamentos.add(medicamento);
    }

    @Override
    public String toString() {
        String str = this.getNome()+ id;
        if(principal){
            str += "(PRINCIPAL) " ;
        }
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
}
