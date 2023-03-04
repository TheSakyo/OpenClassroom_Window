package fr.TheSakyo.Windows.WindowsDisplay.WindowLayout;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.utils.MethodCustom;

import javax.swing.*;
import java.awt.*;

public class WindowBoxLayout extends Window {


    @SuppressWarnings("ConstantConditions")
    public WindowBoxLayout(boolean bool_JPanel, boolean bool_Box) {

        super(300, 120);

        if((bool_JPanel) && (bool_Box) || (!bool_JPanel) && (!bool_Box)) {

            JPanel container = new JPanel();
            JLabel label = new JLabel("Erreur de paramétrage !");

            container.setLayout(new BorderLayout());

            //Définition d'une police d'écriture
            Font police = new Font("Courriel", Font.BOLD + Font.PLAIN, 20);

            MethodCustom.StyleLabel(label, police, Color.RED, null, JLabel.CENTER);

            container.add(label, BorderLayout.NORTH);

            this.setContentPane(container);

        }  else if((bool_JPanel) && (!bool_Box)) {

            JPanel b1 = new JPanel();


            //On définit le layout en lui indiquant qu'il travaillera en ligne
            b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
            b1.add(new JButton("Bouton 1"));

            JPanel b2 = new JPanel();

            //Idem pour cette ligne
            b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
            b2.add(new JButton("Bouton 2"));
            b2.add(new JButton("Bouton 3"));

            JPanel b3 = new JPanel();

            //Idem pour cette ligne
            b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
            b3.add(new JButton("Bouton 4"));
            b3.add(new JButton("Bouton 5"));
            b3.add(new JButton("Bouton 6"));

            JPanel b4 = new JPanel();
            //On positionne maintenant ces trois lignes en colonne
            b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
            b4.add(b1);
            b4.add(b2);
            b4.add(b3);

            this.getContentPane().add(b4);

        } else if((!bool_JPanel) && (bool_Box)) {

            this.setSize(300, 120);

            //On crée un conteneur avec gestion horizontale
            Box b1 = Box.createHorizontalBox();
            b1.add(new JButton("Bouton 1"));
            //Idem
            Box b2 = Box.createHorizontalBox();
            b2.add(new JButton("Bouton 2"));
            b2.add(new JButton("Bouton 3"));
            //Idem
            Box b3 = Box.createHorizontalBox();
            b3.add(new JButton("Bouton 4"));
            b3.add(new JButton("Bouton 5"));
            b3.add(new JButton("Bouton 6"));
            //On crée un conteneur avec gestion verticale
            Box b4 = Box.createVerticalBox();
            b4.add(b1);
            b4.add(b2);
            b4.add(b3);

            this.getContentPane().add(b4);

        }

        this.setVisible(true);

    }
}
