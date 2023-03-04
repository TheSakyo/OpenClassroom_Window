package fr.TheSakyo.Windows.WindowsDisplay.WindowObserver.Form.TextField;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowJTextField extends Window {

    private final JPanel container = new JPanel();
    private final JTextField JTF = new JTextField();
    private final JLabel label = new JLabel("Téléphone");
    private final JButton B = new JButton ("OK");

    public WindowJTextField() {

        super(300, 300);

        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());

        Font font = new Font("Arial", Font.BOLD, 14);

        JTF.setFont(font);
        JTF.setPreferredSize(new Dimension(150, 30));
        JTF.setForeground(Color.BLUE);

        //On ajoute l'écouteur à notre composant
        JTF.addKeyListener(new KeyboardListener());

        JPanel top = new JPanel();
        top.add(label);
        top.add(JTF);
        top.add(B);

        this.setContentPane(top);

        this.setVisible(true);
    }

    class KeyboardListener implements KeyListener {

        public void keyReleased(KeyEvent event) {

            if(!isNumeric(event.getKeyChar())) JTF.setText(JTF.getText().replace(String.valueOf(event.getKeyChar()), ""));
        }

        //Retourne true si le paramètre est numérique, false dans le cas contraire
        private boolean isNumeric(char carac){

            try { Integer.parseInt(String.valueOf(carac)); }
            catch (NumberFormatException e) { return false; }
            return true;
        }

        public void keyPressed(KeyEvent event) {}
        public void keyTyped(KeyEvent event) {}
    }
}
