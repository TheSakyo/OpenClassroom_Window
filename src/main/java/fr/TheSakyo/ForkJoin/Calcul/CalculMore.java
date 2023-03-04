package fr.TheSakyo.ForkJoin.Calcul;

import java.util.concurrent.RecursiveTask;

public class CalculMore extends RecursiveTask<Long> {

    private final long beginning;
    private final long fin;
    private long results;
    private final int SEUIL = 1_000;

    public CalculMore(long beginning, long fin){ this.beginning = beginning; this.fin = fin; }

    protected Long compute() {

        long nombreDeChoseAFaire = fin - beginning;

        if(nombreDeChoseAFaire < SEUIL) {

            System.out.println("Passage en mode MonoThread ou le découpage calcul le résultat");
            results = calcul();
        } else {

            System.out.println("Passage en mode Fork/Join");

            //On découpe la tâche en deux
            long milieu = nombreDeChoseAFaire/2;
            CalculMore calcul1 = new CalculMore(beginning, (beginning+milieu)-1);
            calcul1.fork();

            CalculMore calcul2 = new CalculMore(beginning + milieu, fin);
            results = calcul2.compute() + calcul1.join();
        }
        return results;
    }


    public long calcul(){

        for(long i = beginning; i <= fin; i++) {

            System.out.println(results + " + " + i);
            results += i;
        }
        return results;
    }

    @SuppressWarnings("unused")
    public long getResult(){  return results;  }

}