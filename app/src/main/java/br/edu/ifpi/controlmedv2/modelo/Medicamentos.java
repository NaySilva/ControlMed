package br.edu.ifpi.controlmedv2.modelo;

/**
 * Created by Nayara on 24/03/2016.
 */
public class Medicamentos extends Compromisso{

    private int id;
    private String nome;
    private int dose;
    private String unidade;
    private String instrucao;
    private String frequencia;

    public Medicamentos(String nome, int dose, String unidade, String instrucao, String frequencia) {
        this.nome = nome;
        this.dose = dose;
        this.unidade = unidade;
        this.instrucao = instrucao;
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        String str = "";
        str += "\nNome: " + nome +
                "\nDose: " + dose +
                "\nUnidade: " + unidade +
                "\nFrequencia: " + frequencia +
                "\nInstrução: " + instrucao;
        return str;
    }

    public String text2(){
        return "Nome: " + this.nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public int getDose() {
        return dose;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public void setUnidade(String unidade) {
        unidade = unidade;
    }


    public String getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public void setId(int id) {
        this.id = id;
    }
}

