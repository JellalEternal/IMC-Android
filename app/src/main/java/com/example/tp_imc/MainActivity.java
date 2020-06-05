package com.example.tp_imc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerSize;
    private Spinner spinnerWeight;
    private List<Integer> listValueSize = new ArrayList<>();
    private List<Integer> listValueWeight = new ArrayList<>();

    private EditText name;
    private EditText firstName;

    private Button validButton;
    private Button histButton;
    private DatabaseManager databaseManager;

    private String strFirstName;
    private String strName;
    private double sizeValue;
    private double IMC;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DecimalFormat df = new DecimalFormat("0.00");// 2 chiffres après la virgule

        databaseManager = new DatabaseManager(this);
        databaseManager.insertIMCData("HENRY", "Romuald", 27.00);
        databaseManager.insertIMCData("GENTOT", "Armand", 20.24);
        databaseManager.insertIMCData("GRENOT", "Maxence", 27.00);
        databaseManager.insertIMCData("JOSEAU", "Maxim", 20.58);
        databaseManager.insertIMCData("DECREUS", "Maxence", 26.87);
        databaseManager.insertIMCData("LEGROS", "Victor", 22.00);
        databaseManager.insertIMCData("PLANCQ", "Louis", 23.42);
        databaseManager.insertIMCData("NOGARD", "Hugo", 23.42);
        databaseManager.insertIMCData("HENRY", "Romuald", 18.96);
        databaseManager.insertIMCData("DECREUS", "Maxence", 21.70);
        databaseManager.insertIMCData("STUDIO", "Android", 37.54);
        databaseManager.close();

        name = (EditText) findViewById(R.id.txt_nom);
        firstName = (EditText) findViewById(R.id.txt_prenom);

        validButton = (Button) findViewById(R.id.btn_valider);

        histButton = (Button) findViewById(R.id.btn_historique);

        listValueSize = IntStream.rangeClosed(50, 240).boxed().collect(Collectors.toList());
        spinnerSize = (Spinner) findViewById(R.id.lst_taille);
        ArrayAdapter<Integer> itemsAdapterSize = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listValueSize);
        spinnerSize.setAdapter(itemsAdapterSize);
        spinnerSize.setSelection(120);  // Par défaut 170cm

        listValueWeight = IntStream.rangeClosed(30, 200).boxed().collect(Collectors.toList());
        spinnerWeight = (Spinner) findViewById(R.id.lst_poids);
        ArrayAdapter<Integer> itemsAdapterWeight = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listValueWeight);
        spinnerWeight.setAdapter(itemsAdapterWeight);
        spinnerWeight.setSelection(30); // Par défaut 60kg

        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strFirstName = capitalizeFirstLetter(firstName.getText().toString());
                strName = name.getText().toString().toUpperCase();

                sizeValue = (double) Integer.parseInt(spinnerSize.getSelectedItem().toString()) / (double) 100;
                IMC = Integer.parseInt(spinnerWeight.getSelectedItem().toString()) / Math.pow(sizeValue, 2);

                databaseManager = new DatabaseManager(MainActivity.this);
                databaseManager.insertIMCData(strName, strFirstName, Double.parseDouble(df.format(IMC)));
                databaseManager.close();

                Intent intent = new Intent(MainActivity.this, ResultIMCActivity.class);
                intent.putExtra("prenom", strFirstName);
                intent.putExtra("nom", strName);
                intent.putExtra("IMC", IMC);
                startActivity(intent);
            }
        });
        histButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistActivity.class);
                startActivity(intent);
            }
        });
    }

    public static String capitalizeFirstLetter(String str) {
        return String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1).toLowerCase();
    }
}
