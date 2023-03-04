package fr.TheSakyo.Windows.WindowsDisplay.WindowContainer;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;

public class WindowJSplitPane extends Window {

    //On déclare notre objet JSplitPane
    private final JSplitPane split, split2, split3;

    public WindowJSplitPane() {

        super(200, 200);

        //On crée deux conteneurs de couleurs différentes
        JPanel pan = new JPanel();
        pan.setBackground(Color.blue);

        JPanel pan2 = new JPanel();
        pan2.setBackground(Color.red);

        JPanel pan3 = new JPanel();
        pan3.setBackground(Color.orange);

        JPanel pan4 = new JPanel();
        pan4.setBackground(Color.YELLOW);

        //On construit enfin notre séparateur
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan, pan4);
        //On place le premier séparateur
        split.setDividerLocation(80);

        //On construit notre deuxième séparateur
        split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan3, pan2);
        //On place le deuxième séparateur
        split2.setDividerLocation(100);

        //On passe les deux précédents JSplitPane à celui-ci
        split3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, split, split2);
        //On place le troisième séparateur
        split3.setDividerLocation(80);

        //On le passe ensuite au content pane de notre objet Fenetre
        //placé au centre pour qu'il utilise tout l'espace disponible
        this.getContentPane().add(split3, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
