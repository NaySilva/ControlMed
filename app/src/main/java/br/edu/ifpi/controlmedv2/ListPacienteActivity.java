package br.edu.ifpi.controlmedv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class ListPacienteActivity extends AppCompatActivity {

    Paciente principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_paciente);


        ListView listPacients = (ListView) findViewById(R.id.list_pacient);
        registerForContextMenu(listPacients);
        listPacients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                principal = (Paciente) parent.getItemAtPosition(position);
            }
        });
        Button button = (Button) findViewById(R.id.bNovoPaciente);
        registerForContextMenu(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoPaciente(v);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        recarregarDados();
    }

    private void recarregarDados() {
        final ListView listPacients = (ListView) findViewById(R.id.list_pacient);
        PacienteDAO dao = new PacienteDAO(this);
        List<Paciente> pacientes = dao.lista();
        ArrayAdapter<Paciente> adapter = new ArrayAdapter<Paciente>(this, android.R.layout.simple_list_item_single_choice, pacientes);

        listPacients.setAdapter(adapter);
        listPacients.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listPacients.setTextFilterEnabled(true);
        listPacients.setItemChecked(0, true);



        listPacients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Paciente p = (Paciente) listPacients.getItemAtPosition(position);
                PacienteDAO dao = new PacienteDAO(ListPacienteActivity.this);
                dao.mudarPrincipal(p);
                recarregarDados();
            }
        });
    }

    private void novoPaciente(View v) {

        Intent irParaNovoPaciente = new Intent(this, NovoPacienteActivity.class);
        startActivity(irParaNovoPaciente);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.list_pacient) {
            MenuItem mod = menu.add("Deixar como principal");
            MenuItem deletar = menu.add("Deletar");

            mod.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    PacienteDAO dao = new PacienteDAO(ListPacienteActivity.this);
                    Paciente p = dao.verificarPrincipal();
                    p.setPrincipal(false);
                    principal.setPrincipal(true);
                    return false;
                }
            });


            deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    AlertDialog.Builder b = new AlertDialog.Builder(ListPacienteActivity.this);
                    b.setMessage("Tem certeza?");
                    b.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PacienteDAO dao = new PacienteDAO(ListPacienteActivity.this);
                            dao.remover(principal);
                            onResume();
                            Toast.makeText(ListPacienteActivity.this, "Paciente removido", Toast.LENGTH_SHORT).show();
                        }
                    });
                    b.setNegativeButton("NÃO", null);
                    b.show();

                    return false;
                }
            });
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
