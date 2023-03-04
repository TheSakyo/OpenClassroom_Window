package fr.TheSakyo.Windows.WindowsDisplay.WindowLayout;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;

public class WindowBoderLayout extends Window {

    public WindowBoderLayout() {

        super(300, 300);

        //On définit le layout à utiliser sur le content pane
        this.setLayout(new BorderLayout());

        //On ajoute le bouton au content pane de la JFrame
        //Au centre
        this.getContentPane().add(new JButton("CENTER"), BorderLayout.CENTER);
        //Au nord
        this.getContentPane().add(new JButton("NORTH"), BorderLayout.NORTH);
        //Au sud
        this.getContentPane().add(new JButton("SOUTH"), BorderLayout.SOUTH);
        //À l'ouest
        this.getContentPane().add(new JButton("WEST"), BorderLayout.WEST);
        //À l'est
        this.getContentPane().add(new JButton("EAST"), BorderLayout.EAST);

        this.setVisible(true);
    }
}
