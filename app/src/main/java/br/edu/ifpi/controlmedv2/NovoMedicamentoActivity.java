package br.edu.ifpi.controlmedv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

//import br.edu.ifpi.controlmedv2.dao.MedicamentoDAO;
import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Medicamentos;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class NovoMedicamentoActivity extends AppCompatActivity {

    Paciente principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_medicamento);

    }
    public void gravarClick(View elementoClicado){

        PacienteDAO dao = new PacienteDAO(this);
        principal = dao.verificarPrincipal();

        EditText edNome = (EditText)findViewById(R.id.nomeDoMedicamento);
        EditText edDose = (EditText)findViewById(R.id.dose);
        Spinner spUnidade = (Spinner)findViewById(R.id.unidades);
        Spinner spInstrucao = (Spinner)findViewById(R.id.instrucoes);
        Spinner spFrequencia = (Spinner)findViewById(R.id.frequencias);


        String nome = edNome.getText().toString();
        int dose = Integer.valueOf(edDose.getText().toString());
        String unidade = spUnidade.getSelectedItem().toString();
        String instrucao = spInstrucao.getSelectedItem().toString();
        String frequencia = spFrequencia.getSelectedItem().toString();

        Medicamentos medicamento = new Medicamentos(nome, dose, unidade, instrucao, frequencia);

        //MedicamentoDAO dao = new MedicamentoDAO(this);

        dao.inserirMedicamentos(medicamento, principal);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Medicamento adicionado ;)");
        builder.setPositiveButton("Ok obg!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();


    }


}
