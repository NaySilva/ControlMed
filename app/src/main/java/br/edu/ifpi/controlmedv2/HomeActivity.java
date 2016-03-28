package br.edu.ifpi.controlmedv2;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Data;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class HomeActivity extends AppCompatActivity {

    Paciente p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Data d = new Data();
        TextView textDia = (TextView)findViewById(R.id.diaAtual);
        textDia.setText(d.diaAtual());
        TextView textMes =(TextView)findViewById(R.id.mesAtual);
        textMes.setText(d.mesAtual());
        registerForContextMenu(textDia);
        registerForContextMenu(textMes);

        distribuirFuncoes();

    }

    private void distribuirFuncoes(){
        ImageView imMed = (ImageView)findViewById(R.id.opMedicamentos);
        registerForContextMenu(imMed);

        imMed.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent irParaMedicamentos = new Intent(HomeActivity.this, MedicamentosActivity.class);
                startActivity(irParaMedicamentos);
            }
        });

        ImageView imAgenda = (ImageView)findViewById(R.id.opAgenda);
        registerForContextMenu(imAgenda);

        imAgenda.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent irParaAgenda = new Intent(HomeActivity.this, AgendaActivity.class);
                startActivity(irParaAgenda);
            }
        });

        ImageView imHist = (ImageView)findViewById(R.id.opHistorico);
        registerForContextMenu(imHist);

        imHist.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent irParaHistorico = new Intent(HomeActivity.this, HistoricoActivity.class);
                startActivity(irParaHistorico);
            }
        });

        ImageView imAnalise = (ImageView)findViewById(R.id.opAnalise);
        registerForContextMenu(imAnalise);

        imAnalise.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent irParaAnalise = new Intent(HomeActivity.this, AnaliseActivity.class);
                startActivity(irParaAnalise);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PacienteDAO dao = new PacienteDAO(this);
        p = dao.verificarPrincipal();
        if (p == null){
            Intent irParaNovoPaciente = new Intent(this, NovoPacienteActivity.class);
            startActivity(irParaNovoPaciente);
        }else{
            Toast.makeText(HomeActivity.this, "Ola " + p.getNome(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_perfil){
            //Toast.makeText(HomeActivity.this, "Novo Perfil", Toast.LENGTH_SHORT).show();
            Intent irParaList = new Intent(this, ListPacienteActivity.class);
            startActivity(irParaList);
        }else if(item.getItemId() == R.id.menu_sobre){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String str = "Este aplicativo foi desenvolvido por uma aluna do IFPI - Teresina Central do curso ADS, orientada pelo Professor, com meta de tirar pelo menos um 10.0";
            builder.setMessage(str);
            builder.setPositiveButton("Voltar!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }



}
