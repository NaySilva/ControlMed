package br.edu.ifpi.controlmedv2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.controlmedv2.modelo.Data;
import br.edu.ifpi.controlmedv2.modelo.Medicamentos;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

/**
 * Created by Eva on 26/03/2016.
 */
public class MedicamentoDAO {

    private PacienteDAO dao;

    public MedicamentoDAO(PacienteDAO dao) {
        this.dao = dao;
    }

    public void inserir(Medicamentos medicamento, Paciente paciente){
        ContentValues cv = new ContentValues();
        cv.put("nome", medicamento.getNome());
        cv.put("dose", medicamento.getDose());
        cv.put("unidade", medicamento.getUnidade());
        cv.put("instrucao", medicamento.getInstrucao());
        cv.put("frequencia", medicamento.getFrequencia());
        cv.put("id_paciente", paciente.getId());
       // paciente.addMedicamento(medicamento);

        dao.getWritableDatabase().insert("Medicamento", null, cv);

    }

    public List<Medicamentos> lista(Paciente p){
        List<Medicamentos> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento WHERE id_paciente = " + p.getId() + ";";
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            int dose = c.getInt(c.getColumnIndex("dose"));
            String unidade = c.getString(c.getColumnIndex("unidade"));
            String instrucao = c.getString(c.getColumnIndex("instrucao"));
            String frequencia = c.getString(c.getColumnIndex("frequencia"));
            Medicamentos m = new Medicamentos(nome, dose, unidade, instrucao, frequencia);
            m.setId(id);
          //  p.addMedicamento(m);
            medicamentos.add(m);
        }
        return medicamentos;
    }


    public void remover(Medicamentos medicamento) {
        String[] args = {String.valueOf(medicamento.getId())};
        dao.getWritableDatabase().delete("Medicamento", "id = ?", args);
    }
}
