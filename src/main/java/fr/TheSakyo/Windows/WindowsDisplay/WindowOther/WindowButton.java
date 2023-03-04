package fr.TheSakyo.Windows.WindowsDisplay.WindowOther;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;

public class WindowButton extends Window {

    private final JPanel panel = new JPanel();
    private final JButton button = new JButton("Bouton");
    private final JButton button_background = new JButton("Bouton Fond");

    public WindowButton(boolean button_bg) {

        super(300, 300);

        //Ajout d'un autre bouton au 'ContentPane' par défaut de la fenêtre
        // (Le Bouton occupe toute l'inteface)
        if(button_bg) { this.getContentPane().add(button_background); }

        else {

            //Ajout d'un bouton à notre 'ContentPane'
            panel.add(button);

            //Ajout du 'ContentPane' à notre fenêtre
            this.setContentPane(panel);

        }

        this.setVisible(true);
    }
}
