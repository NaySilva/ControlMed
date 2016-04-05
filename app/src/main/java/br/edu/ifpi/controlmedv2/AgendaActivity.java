package br.edu.ifpi.controlmedv2;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.security.auth.Subject;

import br.edu.ifpi.controlmedv2.Controle.Agenda;
import br.edu.ifpi.controlmedv2.dao.AgendaDAO;
import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Consulta;
import br.edu.ifpi.controlmedv2.modelo.Data;
import br.edu.ifpi.controlmedv2.modelo.Exame;
import br.edu.ifpi.controlmedv2.modelo.Horario;
import br.edu.ifpi.controlmedv2.modelo.Medicamentos;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class AgendaActivity extends AppCompatActivity {

    private Paciente p;
    private PacienteDAO dao = new PacienteDAO(this);
    AgendaDAO daoA = new AgendaDAO(dao);
    private Paciente principal;
    private Button dataButton;
    private ListView listCompromissos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        principal = dao.verificarPrincipal();
        distribuirFuncoes();
    }

    @Override
    protected void onResume() {
        super.onResume();

        recarregarDados();
    }

    private void distribuirFuncoes(){
        dataButton = (Button)findViewById(R.id.bData);
        final Calendar calendar = Calendar.getInstance();
        Data d = new Data();
        dataButton.setText(d.dataAtual());
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AgendaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Data d = new Data();
                        d.setDia(dayOfMonth);
                        d.setMes(monthOfYear);
                        d.setAno(year);
                        dataButton.setText(d.toString());
                        onResume();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        listCompromissos = (ListView) findViewById(R.id.list_compromissos);
        listCompromissos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p = (Paciente) parent.getItemAtPosition(position);
            }
        });

    }

    private void recarregarDados() {
        final List<Agenda> compromissos = daoA.lista(principal, dataButton.getText().toString());
        if (compromissos != null) {
            Toast.makeText(AgendaActivity.this,"Há compromissos", Toast.LENGTH_LONG).show();
            ArrayAdapter<Agenda> adapter = new ArrayAdapter<Agenda>(this, android.R.layout.simple_list_item_checked, compromissos);
//                @Override
//                public View getView(int position, View convertView, ViewGroup parent) {
//
//                    View view = super.getView(position, convertView, parent);
//
//                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//
//                    Agenda ag = compromissos.get(position);
//
//                    text1.setTextSize(20);
//                    text2.setTextSize(16);
//                    text1.setText(ag.toString());
//
//                    if(ag.getTipo().valor==1){
//                        Consulta compromisso = daoA.buscarConsulta(ag.getIdCompromisso());
//                        text2.setText(compromisso.getHospital());
//                    }else if(ag.getTipo().valor==2){
//                        Exame compromisso = daoA.buscarExame(ag.getIdCompromisso());
//                        text2.setText(compromisso.getNome());
//                    }else{
//                        Medicamentos compromisso = daoA.buscarMedicamentos(ag.getIdCompromisso());
//                        text2.setText(compromisso.getNome());
//                    }
//                    //float personalAverage = prefs.getFloat("studentPersonalAverage", -1);
//
//                    return view;
//                }
//            };

            listCompromissos.setAdapter(adapter);

            listCompromissos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

        }else{
            Toast.makeText(AgendaActivity.this,"Não há compromissos", Toast.LENGTH_LONG).show();
        }

//        listCompromissos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//                p = (Paciente) listCompromissos.getItemAtPosition(position);
//                PacienteDAO dao = new PacienteDAO(AgendaActivity.this);
//                dao.mudarPrincipal(p);
//                recarregarDados();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_agenda, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_consulta){
            Intent irParaNovaConsulta = new Intent(this, NovaConsultaActivity.class);
            startActivity(irParaNovaConsulta);
        }else if(item.getItemId() == R.id.add_exame){
            Intent irParaNovoExame = new Intent(this, NovoExameActivity.class);
            startActivity(irParaNovoExame);
        }else if(item.getItemId() == R.id.add_medicamento){
            Intent irParaNovoMedicamento = new Intent(this, NovoMedicamentoActivity.class);
            startActivity(irParaNovoMedicamento);
        }

        return super.onOptionsItemSelected(item);
    }
}
