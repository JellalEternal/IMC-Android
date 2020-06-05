package com.example.tp_imc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IMCData {
    private int id;
    private String nom;
    private String prenom;
    private double IMC;
    private Date date;

    private DateFormat df;

    public IMCData(int id, String nom, String prenom, double IMC, Date date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.IMC = IMC;
        this.date = date;
    }

    public String toString(){
        df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRANCE);
        return prenom + " " + nom + "\n"
                + "IMC : " + IMC + "\n"
                + "Date : " + new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(date) + " à "
                            + new SimpleDateFormat("HH:mm", Locale.FRANCE).format(date);
    }
}

