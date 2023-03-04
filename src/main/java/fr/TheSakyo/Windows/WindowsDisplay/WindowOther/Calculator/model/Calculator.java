package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.model;

public class Calculator extends AbstractModel {

    //Définit l'opérateur
    public void setOperator(String ope) {

        //On lance le calcul
        calcul();

        //On stocke l'opérateur
        this.operator = ope;

        //Si l'opérateur n'est pas = ; On réinitialise l'opérande
        if(!ope.equals("=")) this.operand = "";
    }

    //Définit le nombre
    public void setNumber(String result) {

        //On concatène le nombre
        this.operand += result;

        //On met à jour
        notifyObserver(this.operand);
    }

    //Force le calcul
    public void getResult() { calcul(); }

    //Réinitialise tout
    public void reset() {

        this.result = 0;
        this.operand = "0";
        this.operator = "";

        //Mise à jour !
        notifyObserver(String.valueOf(this.result));
    }

    //Calcul
    public void calcul() {

        //S'il n'y a pas d'opérateur, le résultat est le nombre saisi
        if(this.operator.equals("")) this.result = Double.parseDouble(this.operand);

        else {

            //Si l'opérande n'est pas vide, on calcule avec l'opérateur de calcul
            if (!this.operand.equals("")) {

                if(this.operator.equals("+")) this.result += Double.parseDouble(this.operand);
                if(this.operator.equals("-")) this.result -= Double.parseDouble(this.operand);
                if(this.operator.equals("*")) this.result *= Double.parseDouble(this.operand);
                if(this.operator.equals("/")) {

                    try { this.result /= Double.parseDouble(this.operand); }
                    catch(ArithmeticException e) { this.result = 0; }
                }
            }
        }
        this.operand = "";

        //On lance aussi la mise à jour !
        notifyObserver(String.valueOf(this.result));
    }
}