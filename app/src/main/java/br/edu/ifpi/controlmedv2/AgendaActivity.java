package br.edu.ifpi.controlmedv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ifpi.controlmedv2.enums.TipoDeCompromissoEnum;
import br.edu.ifpi.controlmedv2.modelo.Agenda;
import br.edu.ifpi.controlmedv2.dao.AgendaDAO;
import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.modelo.Data;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class AgendaActivity extends AppCompatActivity {

    private Agenda ag;
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
        setTitle("Sua Agenda " + principal.getNome());
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
        registerForContextMenu(listCompromissos);
        listCompromissos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ag = (Agenda) parent.getItemAtPosition(position);
                return false;
            }
        });

    }

    private void recarregarDados() {
        final List<Agenda> compromissos = daoA.lista(principal, dataButton.getText().toString());
        if (compromissos != null) {
            ArrayAdapter<Agenda> adapter = new ArrayAdapter<Agenda>(this, android.R.layout.simple_list_item_checked, compromissos);
//

            listCompromissos.setAdapter(adapter);
            listCompromissos.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
            int tam = principal.getCompromissos().size();
            for(int i = 0; i < tam; i++ ){
                if(principal.getCompromissos().get(i).isRealizada()){
                    listCompromissos.setItemChecked(i, true);
                }
            }



            listCompromissos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Agenda escolhida = (Agenda) listCompromissos.getItemAtPosition(position);
                    listCompromissos.setItemChecked(position, listCompromissos.isItemChecked(position));
                    daoA.mudarRealizado(escolhida);


                    if ((escolhida.getTipo() == TipoDeCompromissoEnum.CONSULTA) & (escolhida.isRealizada())){
                        AlertDialog.Builder b = new AlertDialog.Builder(AgendaActivity.this);
                        b.setMessage("Deseja adicionar outro compromisso?");

                        b.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder b = new AlertDialog.Builder(AgendaActivity.this);
                                b.setMessage("Escolha o novo compromisso?");

                                b.setPositiveButton("Exame", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent irParaNovoExame = new Intent(AgendaActivity.this, NovoExameActivity.class);
                                        startActivity(irParaNovoExame);
                                    }
                                });

                                b.setNegativeButton("Medicamento", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent irParaNovoMedicamento = new Intent(AgendaActivity.this, NovoMedicamentoActivity.class);
                                        startActivity(irParaNovoMedicamento);
                                    }
                                });
                                b.show();
                            }
                        });

                        b.setNegativeButton("NÃO", null);
                        b.show();
                    }
                }
            });

        }else{
            Toast.makeText(AgendaActivity.this,"Não há compromissos", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.list_compromissos) {
            MenuItem detalhes = menu.add("Detalhes");
            MenuItem deletar = menu.add("Deletar");

            detalhes.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AgendaActivity.this);
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

                    return false;
                }
            });

            deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    AlertDialog.Builder b = new AlertDialog.Builder(AgendaActivity.this);
                    b.setMessage("Tem certeza?");
                    b.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PacienteDAO dao = new PacienteDAO(AgendaActivity.this);
                            daoA.remover(ag);
                            Toast.makeText(AgendaActivity.this, "Compromisso removido", Toast.LENGTH_SHORT).show();
                            onResume();
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

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
