package br.edu.ifpi.controlmedv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifpi.controlmedv2.dao.AgendaDAO;
import br.edu.ifpi.controlmedv2.dao.PacienteDAO;
import br.edu.ifpi.controlmedv2.enums.TipoDeCompromissoEnum;
import br.edu.ifpi.controlmedv2.modelo.Compromisso;
import br.edu.ifpi.controlmedv2.modelo.Consulta;
import br.edu.ifpi.controlmedv2.modelo.Exame;
import br.edu.ifpi.controlmedv2.modelo.Medicamentos;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

public class HistoricoActivity extends AppCompatActivity {

    private Compromisso com;
    private PacienteDAO dao = new PacienteDAO(this);
    private AgendaDAO daoA = new AgendaDAO(dao);
    private Paciente principal;
    private ListView listTodosCompromissos;
    private List<Compromisso> todosCompromissos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        listTodosCompromissos = (ListView) findViewById(R.id.list_todos_compromissos);
        registerForContextMenu(listTodosCompromissos);
        listTodosCompromissos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                com = (Compromisso) parent.getItemAtPosition(position);
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        principal = dao.verificarPrincipal();
        setTitle("Seu Historico " + principal.getNome());
        recarregarDados();

    }

    private void recarregarDados(){
        todosCompromissos = daoA.historico(principal);
        if (todosCompromissos != null) {
            ArrayAdapter<Compromisso> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, todosCompromissos) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    Compromisso a = todosCompromissos.get(position);

                    text1.setTextSize(20);
                    text2.setTextSize(16);
                    text2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    text1.setText(a.text1());

                    if (a.getTipo() == TipoDeCompromissoEnum.CONSULTA){
                        Consulta compromisso = daoA.buscarConsulta(a.getIdCompromisso());
                        text2.setText(compromisso.text2());
                    }else if(a.getTipo() == TipoDeCompromissoEnum.EXAME){
                        Exame compromisso = daoA.buscarExame(a.getIdCompromisso());
                        text2.setText(compromisso.text2());
                    }else if(a.getTipo() == TipoDeCompromissoEnum.MEDICAMENTO){
                        Medicamentos compromisso = daoA.buscarMedicamentos(a.getIdCompromisso());
                        text2.setText(compromisso.text2());
                    }

                    return view;
                }
            };

            listTodosCompromissos.setAdapter(adapter);



            listTodosCompromissos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

        }else{
            Toast.makeText(HistoricoActivity.this, "Não há compromissos", Toast.LENGTH_LONG).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_historico, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.limpar) {
            for (Compromisso com : todosCompromissos) {
                daoA.remover(com);
            }
            Toast.makeText(this, "Historico Limpo", Toast.LENGTH_LONG).show();
            onResume();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.list_todos_compromissos) {
            MenuItem detalhes = menu.add("Detalhes");
            MenuItem deletar = menu.add("Deletar");

            detalhes.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HistoricoActivity.this);
                    String str;
                    if(com.getTipo() == TipoDeCompromissoEnum.CONSULTA) {
                        str = daoA.buscarConsulta(com.getIdCompromisso()).toString();
                    }else if (com.getTipo() == TipoDeCompromissoEnum.EXAME){
                        str = daoA.buscarExame(com.getIdCompromisso()).toString();
                    }else{
                        str = daoA.buscarMedicamentos(com.getIdCompromisso()).toString();
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

                    AlertDialog.Builder b = new AlertDialog.Builder(HistoricoActivity.this);
                    b.setMessage("Tem certeza?");
                    b.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PacienteDAO dao = new PacienteDAO(HistoricoActivity.this);
                            daoA.remover(com);
                            Toast.makeText(HistoricoActivity.this, "Compromisso removido", Toast.LENGTH_SHORT).show();
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
}
