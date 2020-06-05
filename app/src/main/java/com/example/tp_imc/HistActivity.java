package com.example.tp_imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class HistActivity extends AppCompatActivity {
    private Button deleteHistorique;
    private TextView textViewDataIMC;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);

        deleteHistorique = (Button) findViewById(R.id.btn_deleteHistorique);

        textViewDataIMC = (TextView) findViewById(R.id.lbl_dataIMC);

        databaseManager = new DatabaseManager(this);
        List<IMCData> ListDataIMC = databaseManager.getInfoIMC();
        for (IMCData dataIMC : ListDataIMC) {
            textViewDataIMC.append(dataIMC.toString() + "\n\n");
        }
        databaseManager.close();

        deleteHistorique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManager = new DatabaseManager(HistActivity.this);
                databaseManager.deleteIMCHistorique();
                databaseManager.close();
                Intent intent = new Intent(HistActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
