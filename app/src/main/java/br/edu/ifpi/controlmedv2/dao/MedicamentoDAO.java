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
public class MedicamentoDAO extends SQLiteOpenHelper {

    public MedicamentoDAO(Context context) {
        super(context, "controlMed.bd", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Medicamento (" +
                "id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome	TEXT NOT NULL," +
                "dose INTEGER" +
                "unidade INTEGER" +
                "instrucao INTEGER" +
                "frequencia INTEGER" +
                "id_paciente INTEGER" +
                "FOREIGN KEY(`id_paciente`) REFERENCES `Paciente` ( `id` )" +
                ");";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Medicamento;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Medicamentos medicamento, Paciente paciente){
        ContentValues cv = new ContentValues();
        cv.put("nome", medicamento.getNome());
        cv.put("dose", medicamento.getDose());
        cv.put("unidade", medicamento.getUnidade().valor);
        cv.put("instrucao", medicamento.getInstrucao().valor);
        cv.put("frequencia", medicamento.getFrequencia().valor);
        cv.put("id_paciente", paciente.getId());
        paciente.addMedicamento(medicamento);

        getWritableDatabase().insert("Medicamento", null, cv);

    }

//    public Paciente verificarPrincipal(){
//        String sql = "SELECT * FROM Paciente WHERE principal = 1;";
//        Cursor c = getReadableDatabase().rawQuery(sql,null);
//        try{
//            c.moveToFirst();
//            int id = c.getInt(c.getColumnIndex("id"));
//            String nome = c.getString(c.getColumnIndex("nome"));
//            boolean principal = true;
//            Paciente p = new Paciente(nome);
//            p.setId(id);
//            p.setPrincipal(principal);
//            return p;
//        }catch (RuntimeException e){
//            return  null;
//        }
//    }

    public List<Medicamentos> lista(){
        List<Medicamentos> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento;";
        Cursor c = getReadableDatabase().rawQuery(sql,null);

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            int dose = c.getInt(c.getColumnIndex("dose"));
            int unidade = c.getInt(c.getColumnIndex("unidade"));
            int instrucao = c.getInt(c.getColumnIndex("instrucao"));
            int frequencia = c.getInt(c.getColumnIndex("frequencia"));
            Medicamentos p = new Medicamentos(nome);
            p.setId(id);
            medicamentos.add(p);
        }
        return medicamentos;
    }


    public void remover(Medicamentos medicamento) {
        String[] args = {String.valueOf(medicamento.getId())};
        getWritableDatabase().delete("Medicamento", "id = ?", args);
    }
}
