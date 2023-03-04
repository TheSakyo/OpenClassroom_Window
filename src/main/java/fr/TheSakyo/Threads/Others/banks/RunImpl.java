package fr.TheSakyo.Threads.Others.banks;

public record RunImpl(AccBank ab, String name) implements Runnable {

    public void run() {

        for (int i = 0; i < 25; i++) {

            if (this.ab.getBalance() > 0) {

                this.ab.withdrawalSilver(2);
                System.out.println("Retrait effectué par " + this.name);
            }
        }
    }
}
