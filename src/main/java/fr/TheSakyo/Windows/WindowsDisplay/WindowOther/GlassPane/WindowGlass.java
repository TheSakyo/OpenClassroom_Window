package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.GlassPane;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.GlassPane.listeners.MouseGlassListener;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.GlassPane.listeners.MouseGlassMotionListener;

import javax.swing.*;
import java.awt.*;

public class WindowGlass extends Window {

    private final GlassPane glass = new GlassPane();

    public WindowGlass() {

        super(400, 200);

        JPanel pan = new JPanel();
        JPanel pan2 = new JPanel();

        //On crée un composant
        JButton button1 = new JButton("Bouton N°1");

        //On y attache les écouteurs qui auront pour rôle
        //d'initialiser notre glace et d'y affecter les données
        //qui permettront de simuler le déplacement
        button1.addMouseListener(new MouseGlassListener(glass));
        button1.addMouseMotionListener(new MouseGlassMotionListener(glass));

        //On affecte maintenant un TranferHandler spécifique
        //initialisé avec la propriété JavaBean "text"
        button1.setTransferHandler(new TransferHandler("text"));

        JButton button2 = new JButton("Bouton N°2");
        button2.addMouseListener(new MouseGlassListener(glass));
        button2.addMouseMotionListener(new MouseGlassMotionListener(glass));
        button2.setTransferHandler(new TransferHandler("text"));

        JLabel text = new JLabel("Deuxième texte statique");
        text.addMouseListener(new MouseGlassListener(glass));
        text.addMouseMotionListener(new MouseGlassMotionListener(glass));
        text.setTransferHandler(new TransferHandler("text"));

        JLabel label = new JLabel("Texte statique !");
        label.addMouseListener(new MouseGlassListener(glass));
        label.addMouseMotionListener(new MouseGlassMotionListener(glass));
        label.setTransferHandler(new TransferHandler("text"));

        pan.add(button1);
        pan.add(label);
        this.add(pan, BorderLayout.NORTH);

        pan2.add(text);
        pan2.add(button2);
        this.add(pan2, BorderLayout.SOUTH);

        setGlassPane(glass);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
