package br.edu.ifpi.controlmedv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

//import br.edu.ifpi.controlmedv2.dao.MedicamentoDAO;
import br.edu.ifpi.controlmedv2.dao.MedicamentoDAO;
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

        PacienteDAO dao = new PacienteDAO(this);
        principal = dao.verificarPrincipal();

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
            Intent irParaNovoMedicamento = new Intent(this, NovoMedicamento2Activity.class);
            startActivity(irParaNovoMedicamento);
        }

        return super.onOptionsItemSelected(item);
    }

    private void recarregarDados() {
        PacienteDAO dao = new PacienteDAO(this);
        MedicamentoDAO daoM = new MedicamentoDAO(dao);

        ListView listMedicametos = (ListView) findViewById(R.id.list_pacient);
        List<Medicamentos> medicamentos = daoM.lista(principal);
        ArrayAdapter<Medicamentos> adapter = new ArrayAdapter<Medicamentos>(this, android.R.layout.simple_expandable_list_item_1, medicamentos);
        listMedicametos.setAdapter(adapter);

    }
}
