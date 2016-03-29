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

//import br.edu.ifpi.controlmedv2.dao.MedicamentoDAO;
import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Medicamentos;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class MedicamentosActivity extends AppCompatActivity {

    Paciente principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
    }

    @Override
    protected void onResume() {
        recarregarDados();

        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_medicamentos, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_add){
            Intent irParaNovoMedicamento = new Intent(this, NovoMedicamentoActivity.class);
            startActivity(irParaNovoMedicamento);
        }

        return super.onOptionsItemSelected(item);
    }

    private void recarregarDados() {
        PacienteDAO dao = new PacienteDAO(this);
        principal = dao.verificarPrincipal();
        final ListView listMedicametos = (ListView) findViewById(R.id.list_pacient);
        //MedicamentoDAO dao = new MedicamentoDAO(this);
        List<Medicamentos> medicamentos = dao.listaMedicamentos(principal);
        ArrayAdapter<Medicamentos> adapter = new ArrayAdapter<Medicamentos>(this, android.R.layout.simple_expandable_list_item_1, medicamentos);
        listMedicametos.setAdapter(adapter);

        listMedicametos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

            }
        });
    }
}
