package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Animations;

import fr.TheSakyo.utils.MethodCustom;
import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.Windows.WindowsParameters.ForAnimation.WinParamButton;
import fr.TheSakyo.Windows.WindowsParameters.ForAnimation.WinParamPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class WindowAnimation1_0 extends Window implements ActionListener {

    String[] form = {"ROND", "CARRÉ", "TRIANGLE", "ÉTOILE"};

    private final WinParamPanel WP = new WinParamPanel();

    @SuppressWarnings("unused")
    File img_default = new File("src/main/resources/images/buttons/button_default.jpg");

    //private final WinParamButton WB1 = new WinParamButton("Bouton1", null);
    //private final WinParamButton WB2 = new WinParamButton("Bouton2", null);

    private final WinParamButton WB1 = new WinParamButton("Start", img_default);
    private final WinParamButton WB2 = new WinParamButton("Stop", img_default);

    private final JPanel container = new JPanel();
    private final JPanel container_north = new JPanel();
    private final JPanel container_south = new JPanel();

    private final JLabel labelForm = new JLabel("Choix de la forme");

    private final JComboBox<String> combo = new JComboBox<String>(form);
    private final JCheckBox morph = new JCheckBox("Morphing");

    private final JLabel labelClic = new JLabel("Compteur de Clic");

    private boolean backX, backY;

    private boolean animated = true;


    private Thread t;


    //Compteur de clics
    private final Map<WinParamButton, Integer> counter = new HashMap<>();


    public WindowAnimation1_0() {

        super(300, 300);

        container.setLayout(new BorderLayout());
        container_north.setLayout(new BorderLayout());

        container.add(WP, BorderLayout.CENTER);

        JPanel top = new JPanel();
        top.add(labelForm);
        top.add(combo);
        top.add(morph);
        container_north.add(top, BorderLayout.NORTH);


        //Nous ajoutons notre fenêtre à la liste des auditeurs de nos bouton
        WB1.addActionListener(this);
        WB2.addActionListener(this);

        container_south.add(WB1);
        container_south.add(WB2);

        combo.addActionListener(new FormListener());
        morph.addActionListener(new MorphListener());

        container.add(container_south, BorderLayout.SOUTH);

        //Définition d'une police d'écriture
        Font police = new Font("Tahoma", Font.BOLD + Font.PLAIN, 18);

        MethodCustom.StyleLabel(labelClic, police, Color.decode("#3d6fbf"), null, JLabel.CENTER);

        container.add(labelClic, BorderLayout.NORTH);

        this.add(container_north, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);

        WB1.setEnabled(false);
        WB2.setEnabled(true);

        this.setVisible(true);

        animate();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() instanceof WinParamButton WPB) {

            if(this.counter.containsKey(WPB)) { this.counter.replace(WPB, this.counter.get(WPB) + 1);

            } else { this.counter.put(WPB, 1); }

            labelClic.setText(WPB.getText() + " cliqué " + getButtonInteger(WPB) + " fois !");

            if(WPB == WB1) {

                int option = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous lancer l'animation ?",
                        "Lancement de l'animation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if(option != JOptionPane.NO_OPTION &&
                   option != JOptionPane.CLOSED_OPTION) {

                    this.animated = true;

                    t = new Thread(new PlayAnimation());
                    t.start();

                    WB1.setEnabled(false);
                    WB2.setEnabled(true);
                }

            } else if(WPB == WB2) {

                int option = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous arrêter l'animation ?",
                        "Arrêt de l'animation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if(option != JOptionPane.NO_OPTION &&
                   option != JOptionPane.CLOSED_OPTION) {

                    this.animated = false;

                    WB1.setEnabled(true);
                    WB2.setEnabled(false);
                }
            }
        }
    }


    private Integer getButtonInteger(WinParamButton WPB) { return this.counter.get(WPB); }


    private void animate() {

        int x = WP.getPosX();
        int y = WP.getPosY();

        while(this.animated){

            //Si le mode morphing est activé, on utilise la taille actuelle de la forme
            if(WP.isMorph()) {

                if(x < 1) this.backX = false;
                if(x > WP.getWidth() - WP.getDrawSize()) this.backX = true;
                if(y < 1) this.backY = false;
                if(y > WP.getHeight() - WP.getDrawSize()) this.backY = true;

            //Sinon, on fait comme d'habitude
            } else {

                if(x < 1) this.backX = false;
                if(x > WP.getWidth() - 50) this.backX = true;
                if(y < 1) this.backY = false;
                if(y > WP.getHeight() - 50) backY = true;
            }

            if(!this.backX) WP.setPosX(++x);
            else WP.setPosX(--x);
            if(!this.backY) WP.setPosY(++y);
            else WP.setPosY(--y);
            WP.repaint();

            try { MethodCustom.Waiting(3); }
            catch(InterruptedException e) { e.printStackTrace(); }
        }
    }

    class PlayAnimation implements Runnable { public void run() { animate(); } }

    class FormListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //La méthode retourne un Object puisque nous passons des Object dans une liste
            //Il faut donc utiliser la méthode toString() pour retourner un String (ou utiliser un cast)
            WP.setForm(Objects.requireNonNull(combo.getSelectedItem()).toString());
        }
    }

    class MorphListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //Uniquement si la case est cochée, on active le mode morphing
            WP.setMorph(morph.isSelected());
        }
    }
}