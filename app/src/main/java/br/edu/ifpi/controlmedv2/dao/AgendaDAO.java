package br.edu.ifpi.controlmedv2.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.controlmedv2.Controle.Agenda;
import br.edu.ifpi.controlmedv2.enums.TipoDeCompromissoEnum;
import br.edu.ifpi.controlmedv2.modelo.Consulta;
import br.edu.ifpi.controlmedv2.modelo.Data;
import br.edu.ifpi.controlmedv2.modelo.Exame;
import br.edu.ifpi.controlmedv2.modelo.Medicamentos;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

/**
 * Created by Eva on 31/03/2016.
 */
public class AgendaDAO {
    private PacienteDAO dao;

    public AgendaDAO(PacienteDAO dao) {
        this.dao = dao;
    }

    public void inserirConsulta(Agenda agenda, Paciente paciente, Consulta consulta){
        ContentValues cv1 = new ContentValues();
        cv1.put("hospital", consulta.getHospital());
        cv1.put("motivo", consulta.getMotivo());

        dao.getWritableDatabase().insert("Consulta", null, cv1);

        String sql = "SELECT * FROM Consulta ORDER BY id DESC;";
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);
        c.moveToFirst();
        int id = c.getInt(c.getColumnIndex("id"));

        ContentValues cv = new ContentValues();
        cv.put("tipo", 1);
        cv.put("data", agenda.getData().toString());
        cv.put("horario", agenda.getHora().toString());
        cv.put("realizado", 0);
        cv.put("id_paciente", paciente.getId());
        cv.put("id_compromisso", id);


        dao.getWritableDatabase().insert("Agenda", null, cv);

        agenda.setTipo(TipoDeCompromissoEnum.fromInteger(1));
        paciente.addCompromisso(agenda);

    }

    public void inserirExame(Agenda agenda, Paciente paciente, Exame exame){
        ContentValues cv1 = new ContentValues();
        cv1.put("nome", exame.getNome());
        cv1.put("motivo", exame.getMotivo());
        cv1.put("tipo_de_resultado", exame.getTipoDeResultado());

        dao.getWritableDatabase().insert("Exame", null, cv1);

        String sql = "SELECT * FROM Exame ORDER BY id DESC;";
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);
        c.moveToFirst();
        int id = c.getInt(c.getColumnIndex("id"));

        ContentValues cv = new ContentValues();
        cv.put("tipo", 2);
        cv.put("data", agenda.getData().toString());
        cv.put("horario", agenda.getHora().toString());
        cv.put("realizado", 0);
        cv.put("id_paciente", paciente.getId());
        cv.put("id_compromisso", id);

        dao.getWritableDatabase().insert("Agenda", null, cv);

        agenda.setTipo(TipoDeCompromissoEnum.fromInteger(2));
        paciente.addCompromisso(agenda);

    }

    public void inserirMedicamenotos(Agenda agenda, Paciente paciente, Medicamentos medicamento){
        ContentValues cv1 = new ContentValues();
        cv1.put("nome", medicamento.getNome());
        cv1.put("dose", medicamento.getDose());
        cv1.put("unidade", medicamento.getUnidade());
        cv1.put("instrucao", medicamento.getInstrucao());
        cv1.put("frequencia", medicamento.getFrequencia());

        dao.getWritableDatabase().insert("Medicamento", null, cv1);

        String sql = "SELECT * FROM Medicamento ORDER BY id DESC;";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);
        c.moveToFirst();
        int id = c.getInt(c.getColumnIndex("id"));

        ContentValues cv = new ContentValues();
        cv.put("tipo", 3);
        cv.put("data", agenda.getData().toString());
        cv.put("horario", agenda.getHora().toString());
        cv.put("realizado", 0);
        cv.put("id_paciente", paciente.getId());
        cv.put("id_compromisso", id);

        dao.getWritableDatabase().insert("Agenda", null, cv);

        agenda.setTipo(TipoDeCompromissoEnum.fromInteger(3));
        paciente.addCompromisso(agenda);

    }

    public List<Agenda> lista(Paciente p, String data){
        List<Agenda> compromissos = new ArrayList<>();
        String sql = "SELECT * FROM Agenda WHERE id_paciente = " + p.getId() + " AND data = '"+ data + "';";
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            int tipo = c.getInt(c.getColumnIndex("tipo"));
            String hora = c.getString(c.getColumnIndex("horario"));
            String date = c.getString(c.getColumnIndex("data"));
            int id_compromisso = c.getInt(c.getColumnIndex("id_compromisso"));
            Agenda ag = new Agenda(date, hora);
            ag.setTipo(TipoDeCompromissoEnum.fromInteger(tipo));
            ag.setId(id);
            ag.setIdCompromisso(id_compromisso);
            compromissos.add(ag);
        }
        return compromissos;
    }

    public Agenda proximoDeHoje(Paciente p, Data data){
        Agenda proximo;
        String sql = "SELECT * FROM Agenda WHERE id_paciente = " + p.getId() + " AND data = '"+ data.toString() + "' ORDER BY horario ASC;";
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);
        try{
            c.moveToFirst();
            int tipo = c.getInt(c.getColumnIndex("tipo"));
            String hora = c.getString(c.getColumnIndex("horario"));
            String date = c.getString(c.getColumnIndex("data"));
            proximo = new Agenda(date, hora);
            proximo.setTipo(TipoDeCompromissoEnum.fromInteger(tipo));
        }catch (RuntimeException e){
            proximo = null;
        }
        return proximo;
    }

    public Consulta buscarConsulta(int id){
        String sql = "SELECT * FROM Consulta WHERE id = " + id + ";";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);
        c.moveToFirst();
        String hospital = c.getString(c.getColumnIndex("hospital"));
        String motivo = c.getString(c.getColumnIndex("motivo"));
        Consulta consulta = new Consulta(hospital, motivo);
        consulta.setId(id);
        return consulta;
    }

    public Exame buscarExame(int id){
        String sql = "SELECT * FROM Exame WHERE id = " + id + ";";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);
        c.moveToFirst();
        String nome = c.getString(c.getColumnIndex("nome"));
        String motivo = c.getString(c.getColumnIndex("motivo"));
        String tipo_Resultado = c.getString(c.getColumnIndex("tipo_de_resultado"));
        Exame exame = new Exame(nome, motivo, tipo_Resultado);
        exame.setId(id);
        return exame;
    }

    public Medicamentos buscarMedicamentos(int id){
        String sql = "SELECT * FROM Medicamento WHERE id = " + id + ";";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);
        c.moveToFirst();
        String nome = c.getString(c.getColumnIndex("nome"));
        int dose = Integer.valueOf(c.getString(c.getColumnIndex("dose")));
        String unidade = c.getString(c.getColumnIndex("unidade"));
        String frequencia = c.getString(c.getColumnIndex("frequencia"));
        String instrucao = c.getString(c.getColumnIndex("instrucao"));
        Medicamentos med = new Medicamentos(nome, dose, unidade, instrucao, frequencia);
        med.setId(id);
        return med;
    }

//    public List<Medicamentos> lista(Paciente p){
//        List<Medicamentos> medicamentos = new ArrayList<>();
//        String sql = "SELECT * FROM Medicamento WHERE id_paciente = " + p.getId() + ";";
//        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);
//
//        while (c.moveToNext()){
//            int id = c.getInt(c.getColumnIndex("id"));
//            String nome = c.getString(c.getColumnIndex("nome"));
//            int dose = c.getInt(c.getColumnIndex("dose"));
//            String unidade = c.getString(c.getColumnIndex("unidade"));
//            String instrucao = c.getString(c.getColumnIndex("instrucao"));
//            String frequencia = c.getString(c.getColumnIndex("frequencia"));
//            Medicamentos m = new Medicamentos(nome, dose, unidade, instrucao, frequencia);
//            m.setId(id);
//            p.addMedicamento(m);
//            medicamentos.add(m);
//        }
//        return medicamentos;
//    }
//
//
//    public void remover(Medicamentos medicamento) {
//        String[] args = {String.valueOf(medicamento.getId())};
//        dao.getWritableDatabase().delete("Medicamento", "id = ?", args);
//    }
}
