package com.example.tp_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ResultIMCActivity extends AppCompatActivity {
    private String strName;
    private String strFirstName;
    private double IMC;
    private String interpretation;

    private ImageView img;
    private TextView resultWelcome;
    private TextView resultImc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_imc);

        img = (ImageView) findViewById(R.id.imc_logo);

        resultWelcome = (TextView) findViewById(R.id.lbl_resultat);
        resultImc = (TextView) findViewById(R.id.lbl_resultat2);
        DecimalFormat df = new DecimalFormat("0.00");// 2 chiffres après la virgule

        interpretation = "";

        Intent intent = getIntent();

        if (intent.hasExtra("nom")){
            strName = intent.getStringExtra("nom");
        }
        if (intent.hasExtra("prenom")){
            strFirstName = intent.getStringExtra("prenom");
        }
        if (intent.hasExtra("IMC")){
            IMC = intent.getDoubleExtra("IMC", 20.00);

            if(IMC < 18.50){
                interpretation = "insuffisance pondérale (maigreur)";
                img.setImageResource(R.drawable.maigreur);
            }
            if(IMC >= 18.50 && IMC < 25){
                interpretation = "corpulence normale";
                img.setImageResource(R.drawable.normal);
            }
            if(IMC >= 25 && IMC < 30){
                interpretation = "surpoids";
                img.setImageResource(R.drawable.surpoids);
            }
            if(IMC >= 30 && IMC < 35){
                interpretation = "obésité modérée";
                img.setImageResource(R.drawable.obesitemoderee);
            }
            if(IMC >= 35 && IMC <= 40){
                interpretation = "obésité sévère";
                img.setImageResource(R.drawable.obesitesevere);
            }
            if(IMC > 40){
                interpretation = "obésité morbide ou massive";
                img.setImageResource(R.drawable.obesitemorbide);
            }
        }

        String sentence1 = "Bienvenue " + strFirstName + " " + strName;
        resultWelcome.setText(sentence1);
        Toast.makeText(getApplicationContext(), sentence1, Toast.LENGTH_SHORT).show();

        String sentence2 = "Votre IMC est de " + df.format(IMC) + " et vous êtes en " + interpretation;
        resultImc.setText(sentence2);
    }
}
