package fr.TheSakyo.Windows.WindowsDisplay.WindowObserver.Form;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class WindowJComboBox extends Window {

    String[] tab = {"Option 1", "Option 2", "Option 3", "Option 4"};

    private final JPanel container = new JPanel();
    private final JComboBox<String> combo = new JComboBox<String>(tab);
    private final JLabel label = new JLabel("ComboBox");



    public WindowJComboBox() {

        super(300, 300);

        container.setBackground(Color.WHITE);
        container.setLayout(new BorderLayout());

        combo.addItemListener(new ItemState());
        combo.addActionListener(new ItemAction());

        combo.setPreferredSize(new Dimension(100, 20));
        combo.setForeground(Color.BLUE);

        JPanel top = new JPanel();
        top.add(label);
        top.add(combo);

        container.add(top, BorderLayout.NORTH);

        this.setContentPane(container);
        this.setVisible(true);
    }

    //Classe interne implémentant l'interface ItemListener
    static class ItemState implements ItemListener {

        public void itemStateChanged(ItemEvent e) { System.out.println("ItemListener : Événement déclenché sur : " + e.getItem()); }
    }

    //Classe interne implémentant l'interface ActionListener
    class ItemAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.out.println("ActionListener : Action sur " + combo.getSelectedItem());
        }
    }
}
