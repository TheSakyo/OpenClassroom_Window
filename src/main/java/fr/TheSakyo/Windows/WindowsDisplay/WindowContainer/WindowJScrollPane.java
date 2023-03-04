package fr.TheSakyo.Windows.WindowsDisplay.WindowContainer;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowJScrollPane extends Window {

    private final JTextArea textPane = new JTextArea();
    private final JScrollPane scroll = new JScrollPane(textPane);

    public WindowJScrollPane() {

        super(200, 200);

        JButton button =  new JButton("Bouton");

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                System.out.println("Texte écrit dans le JTextArea : ");
                System.out.println("--------------------------------");
                System.out.println(textPane.getText());
            }
        });

        //On ajoute l'objet au content pane de notre fenêtre
        this.getContentPane().add(scroll, BorderLayout.CENTER);
        this.getContentPane().add(button, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
