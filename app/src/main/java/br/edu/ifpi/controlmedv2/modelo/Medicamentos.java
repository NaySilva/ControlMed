package br.edu.ifpi.controlmedv2.modelo;

import br.edu.ifpi.controlmedv2.enums.FrequenciaDeMedicamentoEnum;
import br.edu.ifpi.controlmedv2.enums.InstrucaoDeMedicamentoEnum;
import br.edu.ifpi.controlmedv2.enums.UnidadeDeMedicamentoEnum;

/**
 * Created by Eva on 24/03/2016.
 */
public class Medicamentos {

    private int id;
    private String nome;
    private int dose;
    private UnidadeDeMedicamentoEnum Unidade;
    private Data diaInicial;
    private InstrucaoDeMedicamentoEnum instrucao;
    private FrequenciaDeMedicamentoEnum frequencia;

    public Medicamentos(String nome){
        this.nome = nome;
    }

    public UnidadeDeMedicamentoEnum getUnidade() {
        return Unidade;
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

    public Data getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(Data diaInicial) {
        this.diaInicial = diaInicial;
    }

    public void setUnidade(UnidadeDeMedicamentoEnum unidade) {
        Unidade = unidade;
    }


    public InstrucaoDeMedicamentoEnum getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(InstrucaoDeMedicamentoEnum instrucao) {
        this.instrucao = instrucao;
    }

    public FrequenciaDeMedicamentoEnum getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(FrequenciaDeMedicamentoEnum frequencia) {
        this.frequencia = frequencia;
    }

    public void setId(int id) {
        this.id = id;
    }
}
