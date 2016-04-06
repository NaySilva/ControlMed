package br.edu.ifpi.controlmedv2;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ifpi.controlmedv2.enums.TipoDeCompromissoEnum;
import br.edu.ifpi.controlmedv2.modelo.Agenda;
import br.edu.ifpi.controlmedv2.dao.AgendaDAO;
import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Data;
import br.edu.ifpi.controlmedv2.modelo.Horario;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class HomeActivity extends AppCompatActivity {

    Paciente p;
    PacienteDAO dao = new PacienteDAO(this);

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

    private void proximoCompromisso(){

        TextView textHora = (TextView) findViewById(R.id.proximaHora);
        TextView textTipo = (TextView) findViewById(R.id.proximaTipo);
        final Button detalhes = (Button) findViewById(R.id.bDetalhes);
        try {
            Data d = new Data();
            d.dataAtual();
            Horario h = new Horario();
            h.horaAtual();
            final AgendaDAO daoA = new AgendaDAO(dao);
            final Agenda compromisso = daoA.proximoDeHoje(p, d, h);
            detalhes.setVisibility(View.VISIBLE);
            textHora.setText(compromisso.getHora());
            textTipo.setText(compromisso.getTipo().toString());

            detalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickDetalhes(compromisso, daoA);
                }
            });
        }catch (RuntimeException e){

            textHora.setText("");
            textTipo.setText("Não há compromissos para hoje!");
            detalhes.setVisibility(View.INVISIBLE);
        }
    }

    private void distribuirFuncoes(){

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

    }

    private void clickDetalhes(Agenda ag, AgendaDAO daoA){
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        String str;
        if(ag.getTipo() == TipoDeCompromissoEnum.CONSULTA) {
            str = daoA.buscarConsulta(ag.getIdCompromisso()).toString();
        }else if (ag.getTipo() == TipoDeCompromissoEnum.EXAME){
            str = daoA.buscarExame(ag.getIdCompromisso()).toString();
        }else{
            str = daoA.buscarMedicamentos(ag.getIdCompromisso()).toString();
        }
        builder.setMessage(str);
        builder.setPositiveButton("Voltar!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
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
        acaoInicial();
        proximoCompromisso();
    }

    private void acaoInicial(){
        if (p == null){
            AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
            dialog.setTitle("Bem Vindo Paciente");
            dialog.setCancelable(true);
            LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
            final View dialogView = inflater.inflate(R.layout.activity_novo_paciente, null);
            final EditText edNome = (EditText) dialogView.findViewById(R.id.nomeDoPaciente);
            dialog.setView(dialogView);
            dialog.setPositiveButton(getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Paciente p = new Paciente(edNome.getText().toString());
                    dao.inserir(p);
                    setTitle("Olá " + p.getNome());
                }
            });
            dialog.show();;

        }else{
            setTitle("Olá " + p.getNome());}

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_perfil){
            //Toast.makeText(HomeActivity.this, "Novo Perfil", Toast.LENGTH_SHORT).show();
            Intent irParaList = new Intent(this, ListPacienteActivity.class);
            startActivity(irParaList);
        }else if(item.getItemId() == R.id.menu_sobre){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String str = "Este aplicativo foi desenvolvido por uma aluna do IFPI - Teresina Central do curso ADS, orientada pelo Professor";
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
