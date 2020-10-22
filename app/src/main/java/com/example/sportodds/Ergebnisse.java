package com.example.sportodds;

public class Ergebnisse {
    int späteres;
    int id;
    String date;
    double Gewichtung;
    double Siegwahrscheinlichkeit;
    public Ergebnisse(int id,double Gewichtung,double Siegwahrscheinlichkeit,String date,int späteres){
        this.Gewichtung=Gewichtung;
        this.Siegwahrscheinlichkeit=Siegwahrscheinlichkeit;
        this.date=date;
        this.späteres=späteres;
        this.id=späteres+1;
    }
}
