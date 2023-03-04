package fr.TheSakyo.Windows.WindowsDisplay.WindowObserver.Form.TextField;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

public class WindowJFormattedTextField extends Window {

    private final JPanel container = new JPanel();

    private final JFormattedTextField JTF = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private final JFormattedTextField JTF2 = new JFormattedTextField(NumberFormat.getPercentInstance());

    @SuppressWarnings("UnusedDeclaration")
    private JFormattedTextField JTF_TEL = null;

    private final JLabel label = new JLabel("Un JTextField");
    private final JButton B = new JButton ("OK");

    public WindowJFormattedTextField() {

        super(300, 300);

        try {

            MaskFormatter tel2 = new MaskFormatter("##-##-##-##-##");

            //Vous pouvez ensuite le passer à votre zone de texte
            JTF_TEL = new JFormattedTextField(tel2);

        } catch(ParseException e) { e.printStackTrace(); }


        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());

        Font font = new Font("Arial", Font.BOLD, 14);

        JTF.setFont(font);
        JTF.setPreferredSize(new Dimension(150, 30));
        JTF.setForeground(Color.BLUE);

        JTF2.setPreferredSize(new Dimension(150, 30));

        if(JTF_TEL != null) JTF_TEL.setPreferredSize(new Dimension(150, 30));

        B.addActionListener(new ButtonListener());

        JPanel top = new JPanel();
        top.add(label);
        top.add(JTF);
        top.add(JTF2);
        if(JTF_TEL != null) top.add(JTF_TEL);
        top.add(B);

        this.setContentPane(top);

        this.setVisible(true);
    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.out.println("TEXT : JTF " + JTF.getText());
            System.out.println("TEXT : JTF2 " + JTF2.getText());

            if(JTF_TEL != null) System.out.println("TEXT : JTF_TEL " + JTF_TEL.getText());
        }
    }
}
