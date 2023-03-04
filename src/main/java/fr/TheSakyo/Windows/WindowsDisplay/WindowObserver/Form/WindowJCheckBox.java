package fr.TheSakyo.Windows.WindowsDisplay.WindowObserver.Form;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowJCheckBox extends Window {

    private final JPanel container = new JPanel();
    private final JCheckBox check1 = new JCheckBox("Case 1");
    private final JCheckBox check2 = new JCheckBox("Case 2");

    public WindowJCheckBox() {

        super(300, 300);

        container.setBackground(Color.WHITE);
        container.setLayout(new BorderLayout());

        JPanel top = new JPanel();

        check1.addActionListener(new StateListener());
        check2.addActionListener(new StateListener());

        top.add(check1);
        top.add(check2);

        container.add(top, BorderLayout.NORTH);

        this.setContentPane(container);

        this.setVisible(true);
    }


    static class StateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.out.println("Source : " + ((JCheckBox)e.getSource()).getText() + " - état : " + ((JCheckBox)e.getSource()).isSelected());
        }
    }
}
