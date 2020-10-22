package com.example.sportodds;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences= this.getSharedPreferences("GameSetting", Context.MODE_PRIVATE);
        String s;
        int AnzahlSpieler=sharedPreferences.getInt("AnzahlPlayer",0);
        if (AnzahlSpieler!=0) {
            String[] playerarray = new String[AnzahlSpieler];
            for (int h = 0; h < AnzahlSpieler; h++) {
                s = "Player" + h;
                playerarray[h] = sharedPreferences.getString(s, "alarm");
            }
            Spinner spinner = findViewById(R.id.spinner3);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, playerarray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }
    public void opentableac(View view){
        Intent i=new Intent(this, Tischtennisactivity.class);
        startActivity(i);

    }
    public void spieleradden(View view){
        SharedPreferences sharedPreferences= this.getSharedPreferences("GameSetting", Context.MODE_PRIVATE);
        int AnzahlSpieler=sharedPreferences.getInt("AnzahlPlayer",0);
        if (AnzahlSpieler<10) {
            String a="Player"+ AnzahlSpieler;
            EditText Name = findViewById(R.id.AddPlayer);
            String name = Name.getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(a, name);
            System.out.println("HeyMRS");
            editor.putInt("AnzahlPlayer",AnzahlSpieler+1);
            Name.setText("");
            editor.apply();
        }
        update(view);
    }
    public void update(View view){
        SharedPreferences sharedPreferences= this.getSharedPreferences("GameSetting", Context.MODE_PRIVATE);
        String s;String[] playerarray;
        int AnzahlSpieler=sharedPreferences.getInt("AnzahlPlayer",0);
        if (AnzahlSpieler!=0){
            playerarray = new String[AnzahlSpieler];
            for (int i=0;i<AnzahlSpieler;i++){
                s="Player"+i;
                playerarray[i]=sharedPreferences.getString(s,"alarm");
            }

        }else{
            playerarray=new String[0];

        }
        Spinner deletespinner = findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, playerarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deletespinner.setAdapter(adapter);
    }
    public void deleteplayer(View view){
        SharedPreferences sharedPreferences= this.getSharedPreferences("GameSetting", Context.MODE_PRIVATE);
        Spinner deletespinner = findViewById(R.id.spinner3);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int Spielerclicked=deletespinner.getSelectedItemPosition();
        if (Spielerclicked==-1){
            Toast.makeText(getApplicationContext(),"Super ", LENGTH_SHORT).show();
        }else{
            String s="Player"+Spielerclicked;String s2;
            int AnzahlSpieler=sharedPreferences.getInt("AnzahlPlayer",0);
            for(int i=Spielerclicked;i<AnzahlSpieler-1;i++){
                int p=i+1;
                s2="Player"+p;
                editor.putString(s,sharedPreferences.getString(s2,"hi"));
                s=s2;
            }
            editor.putInt("AnzahlPlayer",AnzahlSpieler-1);
            editor.apply();
        }
        System.out.println();
        update(view);
    }
    public void opendevelop(View view){
        Intent i=new Intent(this, Entwicklungen.class);
        startActivity(i);
    }
}