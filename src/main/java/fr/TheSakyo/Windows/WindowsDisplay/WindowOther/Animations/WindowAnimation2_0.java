package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Animations;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.Windows.WindowsParameters.ForAnimation.WinParamPanel;
import fr.TheSakyo.utils.MethodCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class WindowAnimation2_0 extends Window implements ActionListener {

    private final WinParamPanel WP = new WinParamPanel();

    @SuppressWarnings("unused")
    File img_default = new File("src/main/resources/images/buttons/button_default.jpg");

    private final JPanel container = new JPanel();

    private boolean backX, backY;

    private boolean animated = true;


    private Thread t;

    //La déclaration pour le menu contextuel
    private final JPopupMenu JPM = new JPopupMenu();
    private final JMenu background = new JMenu("Couleur de fond");
    private final JMenu color = new JMenu("Couleur de la forme");

    private final JMenuItem red = new JMenuItem("Rouge");
    private final JMenuItem blue = new JMenuItem("Bleu");
    private final JMenuItem green = new JMenuItem("Vert");
    private final JMenuItem white = new JMenuItem("Blanc");
    private final JMenuItem redBack = new JMenuItem("Rouge");
    private final JMenuItem blueBack = new JMenuItem("Bleu");
    private final JMenuItem greenBack = new JMenuItem("Vert");
    private final JMenuItem whiteBack = new JMenuItem("Blanc");


    private final JMenuBar menuBar = new JMenuBar();

    private final JMenu animation = new JMenu("Animation"),
            shape = new JMenu("Forme"),
            typeShape = new JMenu("Type de forme"),
            about = new JMenu("À propos");

    private final JMenuItem launch = new JMenuItem("Lancer l'animation"),
            stop = new JMenuItem("Arrêter l'animation"),
            exit = new JMenuItem("Quitter"),
            aboutItem = new JMenuItem("?");

    private final JCheckBoxMenuItem morph = new JCheckBoxMenuItem("Morphing");

    private final JRadioButtonMenuItem square = new JRadioButtonMenuItem("Carré"),
            round = new JRadioButtonMenuItem("Rond"),
            triangle = new JRadioButtonMenuItem("Triangle"),
            star = new JRadioButtonMenuItem("Étoile");


    private final ButtonGroup bg = new ButtonGroup();

    //Avec des listeners pour les couleurs
    private final ColorBackgroundListener bgColor = new ColorBackgroundListener();
    private final ColorFormListener frmColor = new ColorFormListener();


    String PathMenu = "src/main/resources/images/menus/";

    //Création de notre barre d'outils
    private final JToolBar toolBar = new JToolBar();

    //Les boutons de la barre d'outils
    private final JButton start_button = new JButton(new ImageIcon(PathMenu + "start.jpg")),
            stop_button = new JButton(new ImageIcon(PathMenu + "stop.jpg")),
            square_button = new JButton(new ImageIcon(PathMenu + "square.jpg")),
            tri_button = new JButton(new ImageIcon(PathMenu + "triangle.jpg")),
            circle_button = new JButton(new ImageIcon(PathMenu + "round.jpg")),
            star_button = new JButton(new ImageIcon(PathMenu + "star.jpg"));


    FormListener fl = new FormListener();


    public WindowAnimation2_0() {

        super(300, 300);

        container.setBackground(Color.WHITE);
        container.setLayout(new BorderLayout());

        //On affecte les écouteurs aux points de menu
        red.addActionListener(frmColor);
        blue.addActionListener(frmColor);
        green.addActionListener(frmColor);
        white.addActionListener(frmColor);

        redBack.addActionListener(bgColor);
        blueBack.addActionListener(bgColor);
        greenBack.addActionListener(bgColor);
        whiteBack.addActionListener(bgColor);


        //On crée et on passe l'écouteur pour afficher le menu contextuel
        //Création d'une implémentation de MouseAdapter
        //avec redéfinition de la méthode adéquate
        WP.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent event) {

                //Seulement s'il s'agit d'un clic droit
                if(event.isPopupTrigger()) {

                    background.add(redBack);
                    background.add(blueBack);
                    background.add(greenBack);
                    background.add(whiteBack);

                    color.add(red);
                    color.add(blue);
                    color.add(green);
                    color.add(white);

                    JPM.add(launch);
                    JPM.add(stop);
                    JPM.add(color);
                    JPM.add(background);

                    //La méthode qui va afficher le menu
                    JPM.show(WP, event.getX(), event.getY());
                }
            }
        });

        container.add(WP, BorderLayout.CENTER);

        this.setContentPane(container);
        this.initMenu();
        this.initToolBar();

        launch.setEnabled(false);
        start_button.setEnabled(false);

        stop.setEnabled(true);
        stop_button.setEnabled(true);


        this.setVisible(true);

        animate();
    }


    private void initToolBar() {

        start_button.addActionListener(this);
        stop_button.addActionListener(this);

        stop_button.setBackground(Color.WHITE);
        start_button.setBackground(Color.WHITE);

        toolBar.add(start_button);
        toolBar.add(stop_button);
        toolBar.addSeparator();

        //Ajout des Listeners
        circle_button.addActionListener(fl);
        circle_button.setBackground(Color.WHITE);
        toolBar.add(circle_button);

        square_button.addActionListener(fl);
        square_button.setBackground(Color.WHITE);
        toolBar.add(square_button);

        tri_button.setBackground(Color.WHITE);
        tri_button.addActionListener(fl);
        toolBar.add(tri_button);

        star_button.setBackground(Color.WHITE);
        star_button.addActionListener(fl);
        toolBar.add(star_button);

        this.add(toolBar, BorderLayout.NORTH);
    }

    private void initMenu() {

        // Attribution raccourcis //
        launch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        stop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));

        morph.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));

        round.setAccelerator(KeyStroke.getKeyStroke('1'));
        square.setAccelerator(KeyStroke.getKeyStroke('2'));
        triangle.setAccelerator(KeyStroke.getKeyStroke('3'));
        star.setAccelerator(KeyStroke.getKeyStroke('4'));

        aboutItem.setAccelerator(KeyStroke.getKeyStroke('?'));
        // Attribution raccourcis //


        //Menu Animation
        //Ajout du listener pour lancer l'animation
        launch.addActionListener(this);
        animation.add(launch);

        //Ajout du listener pour arrêter l'animation
        stop.addActionListener(this);
        stop.setEnabled(false);
        animation.add(stop);

        animation.addSeparator();
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) { System.exit(0); }
        });
        animation.add(exit);

        //Menu Forme

        bg.add(square);
        bg.add(triangle);
        bg.add(round);
        bg.add(star);

        //On crée un nouvel écouteur, inutile de créer 4 instances différentes
        square.addActionListener(fl);
        round.addActionListener(fl);
        triangle.addActionListener(fl);
        star.addActionListener(fl);

        typeShape.add(round);
        typeShape.add(square);
        typeShape.add(triangle);
        typeShape.add(star);

        round.setSelected(true);

        shape.add(typeShape);

        //Ajout du listener pour le morphing
        morph.addActionListener(new MorphListener());
        shape.add(morph);

        //Menu À propos

        //Ajout de ce que doit faire le "?"
        aboutItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String mess = "Merci ! \n J'espère que vous vous amusez bien !\n";
                mess += "Je crois qu'il est temps d'ajouter des accélérateurs et des "+" mnémoniques dans tout ça…\n";
                mess += "\n Allez, GO les ZérOs !";
                JOptionPane.showMessageDialog(null, mess, "À propos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        about.add(aboutItem);

        //Ajout des menus dans la barre de menus
        menuBar.add(animation);
        menuBar.add(shape);
        menuBar.add(about);

        //Ajout des menus dans la barre de menus et ajout de mnémoniques !
        animation.setMnemonic('A');
        menuBar.add(animation);

        shape.setMnemonic('F');
        menuBar.add(shape);

        about.setMnemonic('P');
        menuBar.add(about);

        //Ajout de la barre de menus sur la fenêtre
        this.setJMenuBar(menuBar);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() instanceof JMenuItem || e.getSource() instanceof JButton) {

            if(e.getSource() == launch || e.getSource() == start_button) {

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

                    launch.setEnabled(false);
                    start_button.setEnabled(false);

                    stop.setEnabled(true);
                    stop_button.setEnabled(true);


                }

            } else if(e.getSource() == stop || e.getSource() == stop_button) {

                int option = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous arrêter l'animation ?",
                        "Arrêt de l'animation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if(option != JOptionPane.NO_OPTION &&
                   option != JOptionPane.CLOSED_OPTION) {

                    this.animated = false;

                    launch.setEnabled(true);
                    start_button.setEnabled(true);

                    stop.setEnabled(false);
                    stop_button.setEnabled(false);
                }
            }
        }
    }

    class PlayAnimation implements Runnable { public void run() { animate(); } }

    /**
     * Écoute les menus Forme
     * @author CHerby, TheSakyo
     */
    class FormListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //Si l'action vient d'un bouton radio du menu
            if(e.getSource().getClass().getName().equals("javax.swing.JRadioButtonMenuItem")) {

                WP.setForm(((JRadioButtonMenuItem)e.getSource()).getText());

            } else {

                if(e.getSource() == square_button) { square.doClick(); }
                else if(e.getSource() == tri_button) { triangle.doClick(); }
                else if(e.getSource() == star_button) { star.doClick(); }
                else { round.doClick(); }
            }
        }
    }

    /**
     * Écoute le menu Morphing
     * @author CHerby, TheSakyo
     */
    class MorphListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //Uniquement si la case est cochée, on active le mode morphing
            WP.setMorph(morph.isSelected());
        }
    }


    //Écoute le changement de couleur du fond
    class ColorBackgroundListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == greenBack) WP.setColorBackground(Color.GREEN);
            else if (e.getSource() == blueBack) WP.setColorBackground(Color.BLUE);
            else if(e.getSource() == redBack) WP.setColorBackground(Color.RED);
            else WP.setColorBackground(Color.WHITE);
        }
    }

    //Écoute le changement de couleur du fond
    class ColorFormListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == green) WP.setColorForm(Color.GREEN);
            else if (e.getSource() == blue) WP.setColorForm(Color.BLUE);
            else if(e.getSource() == red) WP.setColorForm(Color.RED);
            else WP.setColorForm(Color.WHITE);
        }
    }
}
