package br.edu.ifpi.controlmedv2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ifpi.controlmedv2.modelo.Agenda;
import br.edu.ifpi.controlmedv2.dao.AgendaDAO;
import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Consulta;
import br.edu.ifpi.controlmedv2.modelo.Data;
import br.edu.ifpi.controlmedv2.modelo.Horario;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class NovaConsultaActivity extends AppCompatActivity  {


    private AutoCompleteTextView hospitalView;
    private EditText motivoView;
    private Button dataButton;
    private Button horaButton;
    private Paciente principal;
    private PacienteDAO dao = new PacienteDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_consulta);

        principal = dao.verificarPrincipal();
        hospitalView = (AutoCompleteTextView) findViewById(R.id.nomeDoHospital);

        motivoView = (EditText) findViewById(R.id.motivo);

        dataButton = (Button) findViewById(R.id.bData);
        final Calendar calendar = Calendar.getInstance();
        Data d = new Data();
        dataButton.setText(d.dataAtual());
        dataButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(NovaConsultaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Data d = new Data();
                        d.setDia(dayOfMonth);
                        d.setMes(monthOfYear);
                        d.setAno(year);
                        dataButton.setText(d.toString());
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        horaButton = (Button)findViewById(R.id.bHora);
        Horario h = new Horario();
        horaButton.setText(h.horarioAtual());
        horaButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(NovaConsultaActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Horario h = new Horario();
                                h.setHora(hourOfDay);
                                h.setMinuto(minute);
                                horaButton.setText(h.toString());
                            }

                        }, (calendar.get(Calendar.HOUR_OF_DAY) + 1), Integer.parseInt("00"), true).show();
            }
        });

        Button novaConsultaButton = (Button) findViewById(R.id.bEnviarConsulta);
        novaConsultaButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarConsulta();
            }
        });

    }



    private void enviarConsulta() {
        AgendaDAO daoA = new AgendaDAO(dao);

        String hospital = hospitalView.getText().toString();
        String motivo = motivoView.getText().toString();

        Consulta consulta = new Consulta(hospital, motivo);

        String data = dataButton.getText().toString();
        String hora = horaButton.getText().toString();

        Agenda agenda = new Agenda(data, hora);

        daoA.inserirConsulta(agenda, principal, consulta);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Medicamento adicionado ;)");
        builder.setPositiveButton("Ok obg!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();
//
    }


}

