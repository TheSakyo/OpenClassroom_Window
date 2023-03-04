package fr.TheSakyo.Windows.WindowsDisplay.WindowLayout;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;

public class WindowGridLayout extends Window {

    public WindowGridLayout() {

        super(300, 300);

        //On définit le layout à utiliser sur le content pane
        //Trois lignes sur deux colonnes avec espacements entres les boutons
        GridLayout gl = new GridLayout();
        gl.setColumns(2); //Définit 2 colonnes
        gl.setRows(3); //Définit 3 lignes
        gl.setHgap(5); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
        gl.setVgap(5); //Cinq pixels d'espace entre les lignes (V comme Vertical)
        this.setLayout(gl);

        //On ajoute le bouton au content pane de la JFrame
        this.getContentPane().add(new JButton("1"));
        this.getContentPane().add(new JButton("2"));
        this.getContentPane().add(new JButton("3"));
        this.getContentPane().add(new JButton("4"));
        this.getContentPane().add(new JButton("5"));
        this.getContentPane().add(new JButton("6"));

        this.setVisible(true);
    }
}
