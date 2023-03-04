package fr.TheSakyo.Windows.WindowsDisplay.WindowOther;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowMenu extends Window {

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu file = new JMenu("Fichier");
    private final JMenu subfile = new JMenu("Sous ficher");
    private final JMenu edit = new JMenu("Edition");

    private final JMenuItem open = new JMenuItem("Ouvrir");
    private final JMenuItem close = new JMenuItem("Fermer");
    private final JMenuItem launch = new JMenuItem("Lancer");
    private final JMenuItem stop = new JMenuItem("Arrêter");

    private final JCheckBoxMenuItem choice1 = new JCheckBoxMenuItem("Choix 1");
    private final JCheckBoxMenuItem choice2 = new JCheckBoxMenuItem("Choix 2");

    private final JRadioButtonMenuItem radio1 = new JRadioButtonMenuItem("Radio 1");
    private final JRadioButtonMenuItem radio2 = new JRadioButtonMenuItem("Radio 2");


    public WindowMenu() {

        super(400, 400);

        //On initialise nos menus
        this.file.add(this.open);

        //On ajoute les éléments dans notre sous-menu
        this.subfile.add(this.choice1);
        this.subfile.add(this.choice2);

        //Ajout d'un séparateur
        this.subfile.addSeparator();

        //On met nos radios dans un ButtonGroup
        ButtonGroup bg = new ButtonGroup();
        bg.add(this.radio1);
        bg.add(this.radio1);

        //On présélectionne la première radio
        this.radio1.setSelected(true);

        this.subfile.add(this.radio1);
        this.subfile.add(this.radio2);

        //Ajout du sous-menu dans notre menu
        this.file.add(this.subfile);
        //Ajout d'un séparateur
        this.file.addSeparator();

        close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) { System.exit(0); }
        });

        this.file.add(this.close);
        this.edit.add(this.launch);
        this.edit.add(this.stop);

        //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
        //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier
        this.menuBar.add(this.file);
        this.menuBar.add(this.edit);

        this.setJMenuBar(this.menuBar);

        this.setVisible(true);
    }
}
