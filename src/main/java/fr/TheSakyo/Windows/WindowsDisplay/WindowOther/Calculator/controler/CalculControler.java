package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.controler;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.model.AbstractModel;

public class CalculControler extends AbstractControler {

    public CalculControler(AbstractModel cal) { super(cal); }

    public void control() {

        //On notifie le modèle d'une action si le contrôle est bon
        //--------------------------------------------------------

        //Si l'opérateur est dans la liste
        if(this.listOperator.contains(this.operator)) {

            //Si l'opérateur est = ; On ordonne au modèle d'afficher le résulta
            if(this.operator.equals("=")) this.calc.getResult();

            //Sinon, on passe l'opérateur au modèle
            else this.calc.setOperator(this.operator);
        }

        //Si le nombre est conforme
        if(this.nbre.matches("^[0-9.]+$")) this.calc.setNumber(this.nbre);

        this.operator = "";
        this.nbre = "";
    }
}
