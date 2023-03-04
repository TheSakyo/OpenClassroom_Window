package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.Observer.Observer;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.controler.AbstractControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowCalculator extends Window  implements Observer {

    private final JPanel container = new JPanel();

    String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
    JButton[] tab_button = new JButton[tab_string.length];

    private JLabel screen = new JLabel();
    private final Dimension dim = new Dimension(50, 40);
    private final Dimension dim2 = new Dimension(50, 31);
    private double number1;
    private final boolean clicOperator = false;
    private final boolean update = false;
    private final String operator = "";

    //L'instance de notre objet contrôleur
    private final AbstractControler controler;

    public WindowCalculator(AbstractControler controler) {

        super(250, 260);
        this.setResizable(false);

        initComposant();

        this.controler = controler;
        this.setContentPane(container);

        this.setVisible(true);
    }


    private void initComposant() {

        Font font = new Font("Arial", Font.BOLD, 20);
        screen = new JLabel("0");
        screen.setFont(font);
        screen.setHorizontalAlignment(JLabel.RIGHT);
        screen.setPreferredSize(new Dimension(220, 20));

        JPanel operator = new JPanel();
        operator.setPreferredSize(new Dimension(55, 225));

        JPanel number = new JPanel();
        number.setPreferredSize(new Dimension(165, 225));

        JPanel panScreen = new JPanel();
        panScreen.setPreferredSize(new Dimension(220, 30));

        //Nous utiliserons le même listener pour tous les opérateurs
        OperatorListener opeListener = new OperatorListener();

        for(int i = 0; i < tab_string.length; i++) {

            tab_button[i] = new JButton(tab_string[i]);
            tab_button[i].setPreferredSize(dim);

            switch(i) {

                case 11 :

                    tab_button[i].addActionListener(opeListener);
                    number.add(tab_button[i]);
                    break;

                case 12 :

                    tab_button[i].setForeground(Color.RED);
                    tab_button[i].addActionListener(new ResetListener());
                    tab_button[i].setPreferredSize(dim2);
                    operator.add(tab_button[i]);
                    break;

                case 13 : case 14 : case 15 : case 16 :

                    tab_button[i].setForeground(Color.RED);
                    tab_button[i].addActionListener(opeListener);
                    tab_button[i].setPreferredSize(dim2);
                    operator.add(tab_button[i]);
                    break;

                default :

                    number.add(tab_button[i]);
                    tab_button[i].addActionListener(new NumberListener());
                    break;
            }
        }

        panScreen.add(screen);
        panScreen.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        container.add(panScreen, BorderLayout.NORTH);
        container.add(number, BorderLayout.CENTER);
        container.add(operator, BorderLayout.EAST);
    }

    //Les listeners pour nos boutons
    class NumberListener implements ActionListener {

        public void actionPerformed(ActionEvent e) { controler.setNumber(((JButton)e.getSource()).getText()); }
    }

    class OperatorListener implements ActionListener {

        public void actionPerformed(ActionEvent e) { controler.setOperator(((JButton)e.getSource()).getText()); }
    }

    class ResetListener implements ActionListener {

        public void actionPerformed(ActionEvent e) { controler.reset(); }
    }

    //Implémentation du pattern observer
    public void update(String str) { screen.setText(str); }
}

