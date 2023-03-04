package fr.TheSakyo.Windows.WindowsDisplay.WindowObserver.Form;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowJRadioBox extends Window {

    private final JPanel container = new JPanel();
    private final JRadioButton jr1 = new JRadioButton("Radio 1");
    private final JRadioButton jr2 = new JRadioButton("Radio 2");

    private final ButtonGroup bg = new ButtonGroup();


    public WindowJRadioBox() {

        super(300, 300);

        container.setBackground(Color.WHITE);
        container.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(jr1);
        top.add(jr2);

        jr1.setSelected(true);
        jr1.addActionListener(new StateListener());
        jr2.addActionListener(new StateListener());

        //On ajoute les boutons au groupe
        bg.add(jr1);
        bg.add(jr2);
        top.add(jr1);
        top.add(jr2);
        container.add(top, BorderLayout.NORTH);

        this.setContentPane(container);

        this.setVisible(true);
    }

    class StateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.out.println("Source : " + jr1.getText() + " - état : " + jr1.isSelected());
            System.out.println("Source : " + jr2.getText() + " - état : " + jr2.isSelected());
        }
    }
}
