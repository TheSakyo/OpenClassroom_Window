package fr.TheSakyo.Windows.WindowsDisplay.WindowLayout;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;

public class WindowGridBagLayout extends Window {

    public WindowGridBagLayout() {

        super(300, 160);

        //On crée nos différents conteneurs de couleur différente
        JPanel Cell1 = new JPanel();
        Cell1.setBackground(Color.YELLOW);
        Cell1.setPreferredSize(new Dimension(60, 40));
        JPanel Cell2 = new JPanel();
        Cell2.setBackground(Color.red);
        Cell2.setPreferredSize(new Dimension(60, 40));
        JPanel Cell3 = new JPanel();
        Cell3.setBackground(Color.green);
        Cell3.setPreferredSize(new Dimension(60, 40));
        JPanel Cell4 = new JPanel();
        Cell4.setBackground(Color.black);
        Cell4.setPreferredSize(new Dimension(60, 40));
        JPanel Cell5 = new JPanel();
        Cell5.setBackground(Color.cyan);
        Cell5.setPreferredSize(new Dimension(60, 40));
        JPanel Cell6 = new JPanel();
        Cell6.setBackground(Color.BLUE);
        Cell6.setPreferredSize(new Dimension(60, 40));
        JPanel Cell7 = new JPanel();
        Cell7.setBackground(Color.orange);
        Cell7.setPreferredSize(new Dimension(60, 40));
        JPanel Cell8 = new JPanel();
        Cell8.setBackground(Color.DARK_GRAY);
        Cell8.setPreferredSize(new Dimension(60, 40));

        //Le conteneur principal
        JPanel Content = new JPanel();
        Content.setPreferredSize(new Dimension(300, 120));
        Content.setBackground(Color.WHITE);
        //On définit le layout manager
        Content.setLayout(new GridBagLayout());

        //L'objet servant à positionner les composants
        GridBagConstraints GBC = new GridBagConstraints();

        //On positionne la case de départ du composant
        GBC.gridx = 0;
        GBC.gridy = 0;
        //La taille en hauteur et en largeur
        GBC.gridheight = 1;
        GBC.gridwidth = 1;
        Content.add(Cell1, GBC);
        //---------------------------------------------
        GBC.gridx = 1;
        Content.add(Cell2, GBC);
        //---------------------------------------------
        GBC.gridx = 2;
        Content.add(Cell3, GBC);
        //---------------------------------------------
        //Cette instruction informe le layout que c'est une fin de ligne
        GBC.gridwidth = GridBagConstraints.REMAINDER;
        GBC.gridx = 3;
        Content.add(Cell4, GBC);
        //---------------------------------------------
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.gridwidth = 1;
        GBC.gridheight = 2;
        //Celle-ci indique que la cellule se réplique de façon verticale
        GBC.fill = GridBagConstraints.VERTICAL;
        Content.add(Cell5, GBC);
        //---------------------------------------------
        GBC.gridx = 1;
        GBC.gridheight = 1;
        //Celle-ci indique que la cellule se réplique de façon horizontale
        GBC.fill = GridBagConstraints.HORIZONTAL;
        GBC.gridwidth = GridBagConstraints.REMAINDER;
        Content.add(Cell6, GBC);
        //---------------------------------------------
        GBC.gridx = 1;
        GBC.gridy = 2;
        GBC.gridwidth = 2;
        Content.add(Cell7, GBC);
        //---------------------------------------------
        GBC.gridx = 3;
        GBC.gridwidth = GridBagConstraints.REMAINDER;
        Content.add(Cell8, GBC);
        //---------------------------------------------
        //On ajoute le conteneur
        this.setContentPane(Content);
        this.setVisible(true);
    }
}
