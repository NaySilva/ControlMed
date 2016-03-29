package br.edu.ifpi.controlmedv2.modelo;

import java.util.Calendar;

/**
 * Created by Eva on 24/03/2016.
 */
public class Data {

    private int id;
    private int dia;
    private int mes;
    private int ano;



    public Data() {
    }


    public String diaAtual(){
        Calendar cal = Calendar.getInstance();
        dia = cal.get(Calendar.DAY_OF_MONTH);
        return dia + "";
    }

    public String mesAtual(){
        Calendar cal = Calendar.getInstance();
        mes = cal.get(Calendar.MONTH)+1;
        switch (mes){
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
            default:
                return "";
        }
    }

    public int getId() {
        return id;
    }
}
