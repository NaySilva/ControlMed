package br.edu.ifpi.controlmedv2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.controlmedv2.modelo.Paciente;

/**
 * Created by Nayara on 24/03/2016.
 */
public class PacienteDAO extends SQLiteOpenHelper{

    public PacienteDAO(Context context) {
        super(context, "controlMed.bd", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Paciente (" +
                    "id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "nome	TEXT NOT NULL," +
                    "principal INTEGER" +
                    ");";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Paciente;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Paciente paciente){
        ContentValues cv = new ContentValues();
        cv.put("nome", paciente.getNome());
        if (verificarPrincipal()!= null){
            cv.put("principal", 0);
        }else{
            cv.put("principal", 1);
            paciente.setPrincipal(true);
        }

        getWritableDatabase().insert("Paciente", null, cv);

    }

    public void mudarPrincipal(Paciente paciente){
        Paciente p = verificarPrincipal();
        String sql = "SELECT * FROM Paciente WHERE id = "+p.getId()+";";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        c.moveToFirst();
        int id = c.getInt(c.getColumnIndex("id"));
        String nome = c.getString(c.getColumnIndex("nome"));
        boolean principal = false;
        Paciente pac = new Paciente(nome);
        pac.setId(id);
        pac.setPrincipal(principal);
        remover(p);
        inserir(pac);

        sql = "SELECT * FROM Paciente WHERE id = "+paciente.getId()+";";
        c = getReadableDatabase().rawQuery(sql,null);
        c.moveToFirst();
        id = c.getInt(c.getColumnIndex("id"));
        nome = c.getString(c.getColumnIndex("nome"));
        principal = true;
        pac = new Paciente(nome);
        pac.setId(id);
        pac.setPrincipal(principal);
        remover(paciente);
        inserir(pac);
    }

    public Paciente verificarPrincipal(){
        String sql = "SELECT * FROM Paciente WHERE principal = 1;";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        try{
            c.moveToFirst();
            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            boolean principal = true;
            Paciente p = new Paciente(nome);
            p.setId(id);
            p.setPrincipal(principal);
            return p;
        }catch (RuntimeException e){
            return  null;
        }
    }

    public List<Paciente> lista(){
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM Paciente ORDER BY principal DESC;";
        Cursor c = getReadableDatabase().rawQuery(sql,null);

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            int princ = c.getInt(c.getColumnIndex("principal"));
            Paciente p = new Paciente(nome);
            if (princ == 1){
                p.setPrincipal(true);
            }
            p.setId(id);
            pacientes.add(p);
        }
        return pacientes;
    }


    public void remover(Paciente paciente) {
        String[] args = {String.valueOf(paciente.getId())};
        getWritableDatabase().delete("Paciente", "id = ?", args);
    }
}
