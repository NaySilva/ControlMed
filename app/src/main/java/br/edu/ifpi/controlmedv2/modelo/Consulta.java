package br.edu.ifpi.controlmedv2.modelo;

/**
 * Created by Eva on 24/03/2016.
 */
public class Consulta {
    private int id;
    private String especialidade;
    private String hospital;
    private String endereco;
    private String motivo;
    private Patologia patalogia;

    public Consulta(String hospital, String motivo) {
        this.hospital = hospital;
        this.motivo = motivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Patologia getPatalogia() {
        return patalogia;
    }

    public void setPatalogia(Patologia patalogia) {
        this.patalogia = patalogia;
    }
}
