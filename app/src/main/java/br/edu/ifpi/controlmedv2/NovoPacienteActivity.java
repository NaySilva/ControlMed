package br.edu.ifpi.controlmedv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class NovoPacienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_paciente);


    }

    public void enviarPaciente(View v){
        EditText edNome = (EditText)findViewById(R.id.nomeDoPaciente);

        Paciente p = new Paciente(edNome.getText().toString());

        PacienteDAO dao = new PacienteDAO(this);
        dao.inserir(p);

        Toast.makeText(this, "Novo Paciente Adicionado!", Toast.LENGTH_SHORT).show();
        finish();

    }

}
