package br.edu.ifpi.controlmedv2.modelo;

/**
 * Created by Eva on 24/03/2016.
 */
public class Exame extends Agenda{
    private int id;
    private String nome;
    private String motivo;
    private double taxa;
    private String tipoDeResultado;

    public Exame(String nome, String motivo, String tipoDeResultado) {
        this.nome = nome;
        this.motivo = motivo;
        this.tipoDeResultado = tipoDeResultado;
    }

    @Override
    public String toString() {
        String str = "";
        str += "\nExame: " + nome +
                "\nMotivo: " + motivo +
                "\nTipo De Resultado: " + tipoDeResultado;
        return str;
    }

    public String text2(){
        return "Nome: " + this.nome;
    }

    public String getTipoDeResultado() {
        return tipoDeResultado;
    }

    public void setTipoDeResultado(String tipoDeResultado) {
        this.tipoDeResultado = tipoDeResultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
}
