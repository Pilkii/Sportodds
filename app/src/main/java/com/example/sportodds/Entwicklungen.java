package com.example.sportodds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entwicklungen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entwicklungen);
        SharedPreferences sharedPreferences = this.getSharedPreferences("GameSetting", Context.MODE_PRIVATE);
        String s;
        int AnzahlSpieler = sharedPreferences.getInt("AnzahlPlayer", 0);
        if (AnzahlSpieler != 0) {
            String[] playerarray = new String[AnzahlSpieler];
            for (int i = 0; i < AnzahlSpieler; i++) {
                s = "Player" + i;
                playerarray[i] = sharedPreferences.getString(s, "alarm");
            }
            Spinner spinner = findViewById(R.id.spinner6);
            Spinner spinner2 = findViewById(R.id.spinner8);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, playerarray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner2.setAdapter(adapter);
        }
    }
    public void Graphladen(View view){
        List<String> Daten=new ArrayList<String>();
        Spinner spinner = findViewById(R.id.spinner6);
        Spinner spinner2 = findViewById(R.id.spinner8);
        Spinner spinner3 = findViewById(R.id.spinner7);
        String Spielerkombination=spinner.getSelectedItem().toString()+0000+spinner2.getSelectedItem().toString();
        String Spielerkombination2=spinner2.getSelectedItem().toString()+0000+spinner.getSelectedItem().toString();
        String Sportart=spinner3.getSelectedItem().toString();
        int Slength=Sportart.length();
        int Splength=Spielerkombination.length();
        SharedPreferences sharedPreferences = this.getSharedPreferences("GameSetting", Context.MODE_PRIVATE);
        int gespeichert=sharedPreferences.getInt("IDC",0);
        for (int i=0;i<gespeichert;i++){
            String Momentan=sharedPreferences.getString(String.valueOf(i),"");
            if (Momentan.substring(0,Slength).equals(Sportart)){
                String substringt = Momentan.substring(Slength, Slength + Splength);
                if (substringt.equals(Spielerkombination)| substringt.equals(Spielerkombination2)){
                    Daten.add(Momentan);
                }
            }
        }


    }
}