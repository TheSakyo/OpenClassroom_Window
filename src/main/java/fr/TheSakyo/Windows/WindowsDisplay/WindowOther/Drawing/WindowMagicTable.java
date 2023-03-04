package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Drawing;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class WindowMagicTable extends Window {

    //LE MENU
    private final JMenuBar menuBar = new JMenuBar();

    JMenu file = new JMenu("Fichier"),
          edit = new JMenu("Edition"),
          form = new JMenu("Forme du pointeur"),
          color = new JMenu("Couleur du pointeur");

    JMenuItem delete = new JMenuItem("Effacer"),
             exit = new JMenuItem("Quitter"),
             round = new JMenuItem("Rond"),
             square = new JMenuItem("Carré"),
             blue = new JMenuItem("Bleu"),
             red = new JMenuItem("Rouge"),
             green = new JMenuItem("Vert");

    //LA BARRE D'OUTILS
    JToolBar toolBar = new JToolBar();

    String PathMenu = "src/main/resources/images/menus/";

    JButton square_button = new JButton(new ImageIcon(PathMenu + "square.jpg")),
            circle_button = new JButton(new ImageIcon(PathMenu + "round.jpg"));

    //LES ÉCOUTEURS
    private final FormListener fListener = new FormListener();
    private final ColorListener cListener = new ColorListener();

    //Notre zone de dessin
    private final DrawPanel drawPanel = new DrawPanel();

    public WindowMagicTable() {

        super(700, 500);

        //On initialise le menu
        this.initMenu();
        //Idem pour la barre d'outils
        this.initToolBar();

        //On positionne notre zone de dessin
        this.getContentPane().add(drawPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    //Initialise le menu
    private void initMenu() {

        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) { drawPanel.erase(); }
        });

        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));

        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

        file.add(delete);
        file.addSeparator();
        file.add(exit);
        file.setMnemonic('F');

        square.addActionListener(fListener);
        round.addActionListener(fListener);
        form.add(round);
        form.add(square);

        red.addActionListener(cListener);
        red.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        green.addActionListener(cListener);
        green.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        blue.addActionListener(cListener);
        blue.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
        color.add(red);
        color.add(green);
        color.add(blue);

        edit.setMnemonic('E');
        edit.add(form);
        edit.addSeparator();
        edit.add(color);

        menuBar.add(file);
        menuBar.add(edit);

        this.setJMenuBar(menuBar);
    }

    //Initialise la barre d'outils
    private void initToolBar() {

        square_button.addActionListener(fListener);
        circle_button.addActionListener(fListener);

        toolBar.add(square_button);
        toolBar.add(circle_button);

        toolBar.addSeparator();

        this.getContentPane().add(toolBar, BorderLayout.NORTH);
    }



    //ÉCOUTEUR POUR LE CHANGEMENT DE FORME
    class FormListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if(e.getSource().getClass().getName().equals("javax.swing.JMenuItem")) {

                if(e.getSource() == square) drawPanel.setPointerType("SQUARE");
                else drawPanel.setPointerType("CIRCLE");

            } else {

                if(e.getSource() == square_button) drawPanel.setPointerType("SQUARE");
                else drawPanel.setPointerType("CIRCLE");
            }
        }
    }


    //ÉCOUTEUR POUR LE CHANGEMENT DE COULEUR
    class ColorListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == green) drawPanel.setPointerColor(Color.GREEN);
            else if(e.getSource() == blue) drawPanel.setPointerColor(Color.BLUE);
            else drawPanel.setPointerColor(Color.RED);
        }
    }
}
