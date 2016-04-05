package br.edu.ifpi.controlmedv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

//import br.edu.ifpi.controlmedv2.dao.MedicamentoDAO;
import br.edu.ifpi.controlmedv2.dao.MedicamentoDAO;
import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Medicamentos;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class NovoMedicamento2Activity extends AppCompatActivity {

    Paciente principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_medicamento2);

        Button gravar = (Button)findViewById(R.id.bEnviarMedicamento);
        gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravarClick(v);
            }
        });

    }
    private void gravarClick(View elementoClicado){

        PacienteDAO dao = new PacienteDAO(this);
        principal = dao.verificarPrincipal();
        MedicamentoDAO daoM = new MedicamentoDAO(dao);

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

        daoM.inserir(medicamento, principal);

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
