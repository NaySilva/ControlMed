package br.edu.ifpi.controlmedv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class MedicamentosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_medicamentos, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_add){
            Intent irParaList = new Intent(this, ListPacienteActivity.class);
            startActivity(irParaList);
        }

        return super.onOptionsItemSelected(item);
    }

//    private void recarregarDados() {
//        final ListView listPacients = (ListView) findViewById(R.id.list_pacient);
//        Medicamentos dao = new PacienteDAO(this);
//        List<Paciente> pacientes = dao.lista();
//        ArrayAdapter<Paciente> adapter = new ArrayAdapter<Paciente>(this, android.R.layout.simple_expandable_list_item_1, pacientes);
//
//        listPacients.setAdapter(adapter);
//        listPacients.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
//        listPacients.setTextFilterEnabled(true);
//        listPacients.setItemChecked(0, true);
//
//
//
//        listPacients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//                Paciente p = (Paciente)listPacients.getItemAtPosition(position);
//                PacienteDAO dao = new PacienteDAO(ListPacienteActivity.this);
//                dao.mudarPrincipal(p);
//                recarregarDados();
//            }
//        });
//    }
}
