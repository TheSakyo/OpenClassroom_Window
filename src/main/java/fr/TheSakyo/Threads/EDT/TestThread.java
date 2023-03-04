package fr.TheSakyo.Threads.EDT;

import fr.TheSakyo.utils.MethodCustom;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestThread {

    static int count = 0;
    static JButton button = new JButton("Pause");

    public void WindowThread() {

        JFrame Win = new JFrame("EDT");

        Win.getContentPane().add(button);
        Win.setSize(200, 100);
        Win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Win.setLocationRelativeTo(null);

        Win.setVisible(true);

        updateButton();

        System.out.println("Reprise du thread principal");
    }



    public static void updateButton(){

        //On crée un Worker générique, cette fois
        SwingWorker sw = new SwingWorker<Integer, String>() {

            protected Integer doInBackground() {

                int i;
                for(i = 0; i < 5; i++) {

                    try {

                        //On change la propriété d'état
                        setProgress(i);

                        //On publie un résultat intermédiaire
                        publish("Tour de boucle N° " + (i + 1));

                        MethodCustom.Waiting(1000);

                    } catch(InterruptedException e) { e.printStackTrace(); }
                }
                return i;
            }

            public void done() {

                if(SwingUtilities.isEventDispatchThread()) System.out.println("Dans l'EDT ! ");

                //On utilise la méthode get() pour récupérer le résultat de la méthode doInBackground()
                try { button.setText("Traitement terminé au bout de "+get()+" fois !"); }
                catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
            }

            //La méthode gérant les résultats intermédiaires
            public void process(List<String> list) { for(String str : list) System.out.println(str); }
        };

        //On écoute le changement de valeur pour la propriété
        sw.addPropertyChangeListener(new PropertyChangeListener() {

            //Méthode de l'interface
            public void propertyChange(PropertyChangeEvent e) {

                //On vérifie tout de même le nom de la propriété
                if("progress".equals(e.getPropertyName())) {

                    if(SwingUtilities.isEventDispatchThread()) System.out.println("Dans le listener donc dans l'EDT ! ");

                    //On récupère sa nouvelle valeur
                    button.setText("Pause " + e.getNewValue());
                }
            }
        });

        //On lance le SwingWorker
        sw.execute();
    }
}