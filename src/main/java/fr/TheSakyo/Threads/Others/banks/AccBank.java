package fr.TheSakyo.Threads.Others.banks;

public class AccBank {

    private int solde = 100;

    public int getBalance() {

        if(this.solde < 0) System.out.println("Vous êtes à découvert !");

        return this.solde;
    }

    public synchronized void withdrawalSilver(int retrait ){

        solde = solde - retrait;
        System.out.println("Solde = " + solde);
    }
}
