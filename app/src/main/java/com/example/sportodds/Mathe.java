package com.example.sportodds;

import java.util.Arrays;

public class Mathe {
    private static boolean mitrange = false;
    public static double[][] facs;
    private static double Siegodds;
    private static int MaxPunkteproGame;
    private static int Satzlänge;
    private static int AbstandSieg;
    public static double[] daswurdeberechneet;
    static int WGB;

    public static double manager(double obereslimit, double untereslimit, int Aufrufcounter){
        double Mitte=(obereslimit+untereslimit)/2;
        if (Aufrufcounter==100) {
            return Mitte;
        }
        double probability = oddsgeber(Mitte);
        if (probability>Siegodds) {
            return manager(Mitte, untereslimit, Aufrufcounter + 1);
        }else{
            return manager(obereslimit,Mitte,Aufrufcounter+1);}
    }

    public static double oddsgeber(double aktuelleOdds){
            return oddsinregtime(aktuelleOdds)+oddsoverregtime(aktuelleOdds);
    }
    public static double oddsinregtime(double aktuelleOdds){
        double odds=0;
        for (int i=Satzlänge;i<=MaxPunkteproGame;i++) { //Wahrscheinlichkeit zu gewinnen ohne Verlängerung
            odds += calculateBinomialCoefficient(MaxPunkteproGame,i)*Math.pow(aktuelleOdds, i)*Math.pow(1-aktuelleOdds, MaxPunkteproGame-i);
            System.out.println(calculateBinomialCoefficient(MaxPunkteproGame,i)+"oddsinregtime c");
        }
        return odds;
    }
    public static double oddsoverregtime(double aktuelleOdds){
        double odds=0;
        daswurdeberechneet=new double[(int)AbstandSieg*4];
        Arrays.fill(daswurdeberechneet, 10000);
        if (Satzlänge<71) WGB=5000;
        else WGB=(int)Math.pow(Satzlänge, 2);
        if (AbstandSieg>1) {int[] op=possibleGamestates();
            for (int i=0;i<2*(AbstandSieg-1);i+=2) {
                int Scdiff=op[i]-op[i+1]+AbstandSieg;
                double vl=calculateBinomialCoefficient(MaxPunkteproGame,op[i])*Math.pow(aktuelleOdds, op[i])*Math.pow(1-aktuelleOdds, op[1+i]);
                odds+=vl*helper(aktuelleOdds,Scdiff,0);
            }
        }
        return odds;

    }
    public static double helper(double p, int Scordiff, int recschritt) {
        if (recschritt == WGB) return 0;
        if (Scordiff == 2 * AbstandSieg) return 1;
        else if (Scordiff == 0) return 0;
        if (daswurdeberechneet[Scordiff + 2 * AbstandSieg] <= recschritt)
            return daswurdeberechneet[(int) Scordiff];
        else {
            daswurdeberechneet[Scordiff] = p * helper(p, Scordiff + 1, recschritt + 1) + (1 - p) * helper(p, Scordiff - 1, recschritt + 1);
            daswurdeberechneet[Scordiff + 2 * AbstandSieg] = recschritt;
            return daswurdeberechneet[Scordiff];
        }
    }
    public static int[] possibleGamestates(){
        int x=2*(AbstandSieg-1);
        int maxlänge=Satzlänge*2-AbstandSieg;
        int[] options=new int[(int) x]; int oc=0;
        for(int i=Satzlänge-AbstandSieg+1;i<Satzlänge;i++) {
            options[oc]=i;
            options[oc+1]=maxlänge-i;
            oc+=2;
        }
        return options;
    }
    public static double calculateBinomialCoefficient(int n, int k){
        return (facs[k][n]);
    }
    public static void factorialmemory(int höhe){
        facs=new double[höhe+1][höhe+1];
        for (int i=0;i<=höhe;i++){facs[0][i]=1;facs[i][i]=1;}
        for (int i=2;i<=höhe;i++) {
            for (int j=1;j<i;j++) {
                facs[j][i]=facs[j][i-1]+facs[j-1][i-1];
            }
        }
    }

    public static void  initialmemory(int AbstandSieg, boolean mitrange, int Satzlänge, double Siegodds){
        MaxPunkteproGame=Satzlänge*2-AbstandSieg;
        Mathe.Satzlänge =Satzlänge;
        Mathe.AbstandSieg =AbstandSieg;
        Mathe.mitrange=mitrange;
        Mathe.Siegodds =Siegodds;
    }
    public static double aufruf(int siegefirst, int siegesecond, int AbstandSieg, int PunkteproSatz, boolean mitrange){
        double Siegodds=Double.parseDouble(String.valueOf(siegefirst));;
        Siegodds/=Double.parseDouble(String.valueOf(siegefirst+siegesecond));
        initialmemory(AbstandSieg,mitrange,PunkteproSatz,Siegodds);
        factorialmemory(2*PunkteproSatz);
        return manager(1,0,0);
    }
}
