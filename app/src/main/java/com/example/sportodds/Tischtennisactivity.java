package com.example.sportodds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Set;

import static android.widget.Toast.LENGTH_SHORT;

public class Tischtennisactivity extends AppCompatActivity {
    double p=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tischtennisactivity);
        SharedPreferences sharedPreferences= this.getSharedPreferences("GameSetting", Context.MODE_PRIVATE);
        String s;
        int AnzahlSpieler=sharedPreferences.getInt("AnzahlPlayer",0);
        if (AnzahlSpieler!=0){
            String[] playerarray = new String[AnzahlSpieler];
        for (int i=0;i<AnzahlSpieler;i++){
            s="Player"+i;
            playerarray[i]=sharedPreferences.getString(s,"alarm");
        }
            Spinner spinner = findViewById(R.id.spinner);
            Spinner spinner2=findViewById(R.id.spinner2);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, playerarray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner2.setAdapter(adapter);
        }else{
            System.out.println("ALARM");
        }
    }
    public void getodds(View view){
        EditText siegef= findViewById(R.id.editTextNumber2);
        String s0 = siegef.getText().toString();
        EditText sieges= findViewById(R.id.editTextNumber3);
        String s1 = sieges.getText().toString();
        EditText Punkte= findViewById(R.id.editTextNumber);
        String s2 = Punkte.getText().toString();
        RadioButton cja=findViewById(R.id.JaAb);
        EditText AbstandS= findViewById(R.id.editTextNumber4);
        String s3;
       // CheckBox mitrange=findViewById(R.id.checkBox);
        int siegefirst=Integer.parseInt(s0);
        int siegesecond=Integer.parseInt(s1);
        if (cja.isChecked()){
            s3=AbstandS.getText().toString();
        }else{
            s3="1";
        }
        int AbstandSieg=Integer.parseInt(s3);
        int PunkteproSatz=Integer.parseInt(s2);
        double odds=Mathe.aufruf(siegefirst,siegesecond,AbstandSieg,PunkteproSatz,true);
        p=odds;
        Toast.makeText(getApplicationContext(),"Super "+odds, LENGTH_SHORT).show();
    }
    public void disableab(View view){
        EditText editTextNumber4=findViewById(R.id.editTextNumber4);
        editTextNumber4.setFocusable(false);
    }
    public void ableab(View view){
        EditText editTextNumber4=findViewById(R.id.editTextNumber4);
        editTextNumber4.setFocusableInTouchMode(true);
    }
    public void ergebnisspeichern(View view){
        Switch switch1=findViewById(R.id.switch1);
        if (!switch1.isChecked()){
            Toast.makeText(getApplicationContext(),"Um sich ein Ergebnis zu merken müssen Player angegeben werden.", LENGTH_SHORT).show();
        }else {
            Spinner spinner=findViewById(R.id.spinner);
            Spinner spinner2=findViewById(R.id.spinner2);
            String Spielerkombination=spinner.getSelectedItem().toString()+0000+spinner2.getSelectedItem().toString();
            EditText siegef= findViewById(R.id.editTextNumber2);
            String s0 = siegef.getText().toString();
            EditText sieges= findViewById(R.id.editTextNumber3);
            String s1 = sieges.getText().toString();
            String Gewicht=String.valueOf(Integer.parseInt(s0)+Integer.parseInt(s1));
            String Wahrscheinlichkeiten=String.valueOf(p);
            Calendar c=Calendar.getInstance();
            String Date= DateFormat.getDateInstance().format(c.getTime());
            SharedPreferences sharedPreferences = this.getSharedPreferences("GameSetting", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int thisId=sharedPreferences.getInt("IDC",0);
            editor.putString(String.valueOf(thisId), "Tischtennis"+Spielerkombination+0000+Gewicht+0000+Wahrscheinlichkeiten+0000+Date);
            editor.putInt("IDC",thisId+1);
            editor.apply();
        }
    }

}