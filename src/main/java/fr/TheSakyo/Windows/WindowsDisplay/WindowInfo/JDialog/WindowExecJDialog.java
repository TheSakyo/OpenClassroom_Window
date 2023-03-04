package fr.TheSakyo.Windows.WindowsDisplay.WindowInfo.JDialog;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowExecJDialog extends Window {

    private final JButton button = new JButton("Appel à la WindowJDialog");

    public WindowExecJDialog() {

        super(300, 300);

        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(button);

        button.addActionListener(new DialogListener());

        this.setVisible(true);
    }


    static class DialogListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            WindowJDialog WJD = new WindowJDialog(null, "Création du Personnage", true);
            WindowJDialogInfo WJDInfo = WJD.showWinJDialog();


            JOptionPane.showMessageDialog(null, WJDInfo.toString(), "Informations personnage", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
