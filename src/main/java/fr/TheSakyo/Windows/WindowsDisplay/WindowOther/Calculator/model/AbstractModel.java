package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.model;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.Observer.Observable;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.Observer.Observer;

import java.util.ArrayList;

public abstract class AbstractModel implements Observable {

    protected double result = 0;
    protected String operator = "", operand = "";
    private ArrayList<Observer> listObserver = new ArrayList<>();

    //Efface
    public abstract void reset();

    //Effectue le calcul
    public abstract void calcul();

    //Affichage forcé du résultat
    public abstract void getResult();

    //Définit l'opérateur de l'opération
    public abstract void setOperator(String operator);

    //Définit le nombre à utiliser pour l'opération
    public abstract void setNumber(String nbre) ;

    //Implémentation du pattern observer
    public void addObserver(Observer obs) { this.listObserver.add(obs); }

    public void notifyObserver(String str) {

        if(str.matches("^0[0-9]+")) str = str.substring(1);

        for(Observer obs : listObserver) obs.update(str);
    }

    public void removeObserver() { listObserver = new ArrayList<Observer>(); }
}
