package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.controler;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.model.AbstractModel;

import java.util.ArrayList;

public abstract class AbstractControler {

    protected AbstractModel calc;
    protected String operator = "", nbre = "";
    protected ArrayList<String> listOperator = new ArrayList<String>();

    public AbstractControler(AbstractModel cal) {

        this.calc = cal;

        //On définit la liste des opérateurs
        //Afin de s'assurer qu'ils sont corrects
        this.listOperator.add("+");
        this.listOperator.add("-");
        this.listOperator.add("*");
        this.listOperator.add("/");
        this.listOperator.add("=");
    }

    //Définit l'opérateur
    public void setOperator(String ope) {

        this.operator = ope;
        control();
    }

    //Définit le nombre
    public void setNumber(String nombre) {

        this.nbre = nombre;
        control();
    }

    //Efface
    public void reset() { this.calc.reset(); }

    //Méthode de contrôle
    abstract void control();
}
