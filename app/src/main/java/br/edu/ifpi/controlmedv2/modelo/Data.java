package br.edu.ifpi.controlmedv2.modelo;

import java.util.Calendar;

/**
 * Created by Nayara on 24/03/2016.
 */
public class Data {

    private int id;
    private int dia;
    private int mes;
    private int ano;



    public Data() {
    }

    @Override
    public String toString(){
        String str = String.format("%02d", dia) + "/" + String.format("%02d", mes) + "/" + String.format("%04d", ano);
        return str;
    }
     public String dataAtual(){
         diaAtual();
         mesAtual();
         anoAtual();
         return toString();
     }

    public String diaAtual(){
        Calendar cal = Calendar.getInstance();
        dia = cal.get(Calendar.DAY_OF_MONTH);
        return String.format("%02d", dia);
    }

    public String mesAtual(){
        Calendar cal = Calendar.getInstance();
        mes = cal.get(Calendar.MONTH)+1;
        return nomeDoMes();
    }

    public String anoAtual(){
        Calendar cal = Calendar.getInstance();
        ano = cal.get(Calendar.YEAR);
        return String.format("%04d", ano);
    }

    public String nomeDoMes(){
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

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes + 1;
    }

    public int getAno() {
        return ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getId() {
        return id;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
