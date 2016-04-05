package br.edu.ifpi.controlmedv2.Controle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import br.edu.ifpi.controlmedv2.HomeActivity;
import br.edu.ifpi.controlmedv2.R;
import br.edu.ifpi.controlmedv2.modelo.Paciente;

/**
 * Created by Eva on 31/03/2016.
 */
public class HomeControl {

//    public void dialogAddPaciente(Paciente p, View v){
//        AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.getAplicationContext());
//        dialog.setTitle("Bem Vindo Paciente");
//        dialog.setCancelable(true);
//        LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
//        final View dialogView = inflater.inflate(R.layout.activity_novo_paciente, null);
//        final EditText edNome = (EditText) dialogView.findViewById(R.id.nomeDoPaciente);
//        dialog.setView(dialogView);
//        dialog.setPositiveButton(getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Paciente p = new Paciente(edNome.getText().toString());
//                dao.inserir(p);
//            }
//        });
//        dialog.show();
//    }

}
