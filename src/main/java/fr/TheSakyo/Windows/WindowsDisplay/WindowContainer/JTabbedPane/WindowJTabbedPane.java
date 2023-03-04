package fr.TheSakyo.Windows.WindowsDisplay.WindowContainer.JTabbedPane;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.utils.MethodCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class WindowJTabbedPane extends Window {

    ImageIcon icon = new ImageIcon(PanelTabbed.java_img);

    public WindowJTabbedPane() {

        super(400, 200);

        //Création de plusieurs Panneau
        new PanelTabbed(Color.RED, null, null);
        new PanelTabbed(Color.GREEN, null, null);
        new PanelTabbed(Color.BLUE, null, null);


        //On passe ensuite les tabs au content pane
        this.getContentPane().add(PanelTabbed.getTab(), BorderLayout.CENTER);



        //Ajout du bouton pour ajouter des onglets
        JButton add = new JButton("Ajouter un onglet");
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String[] color_array = { "BLACK", "WHITE", "DARK_GRAY", "LIGHT_GRAY", "GRAY", "PINK", "ORANGE", "YELLOW", "GREEN", "MAGENTA", "RED", "CYAN", "BLUE" };

                List<String> color_list = Arrays.stream(color_array).toList();

                String message = "Veuillez définir une couleur " + color_list;

                String color = JOptionPane.showInputDialog(null, message,"Choix de la couleur", JOptionPane.QUESTION_MESSAGE);

                if(color_list.contains(color)) {

                    PanelTabbed.getTab().addTab("Onglet n° " + (PanelTabbed.getCount()), icon, new PanelTabbed(MethodCustom.getColor(color), null, null));

                } else { JOptionPane.showMessageDialog(null, "La couleur spécifié est introuvable !", "Erreur", JOptionPane.ERROR_MESSAGE); }
            }
        });

        //Ajout du bouton pour retirer l'onglet sélectionné
        JButton delete = new JButton("Effacer l'onglet");
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //On récupère l'index de l'onglet sélectionné
                int selected = PanelTabbed.getTab().getSelectedIndex();

                //S'il n'y a plus d'onglet, la méthode ci-dessus retourne -1
                if(selected > -1) {

                    PanelTabbed.getTab().removeTabAt(selected);

                    // Réactualise le nombre des onglets //
                    for(int i = 0; i < PanelTabbed.getTab().getTabCount(); i++) {

                        Component comp = PanelTabbed.getTab().getComponentAt(i);

                        Color color = PanelTabbed.getBackgroundColor(comp);

                        String title = "Onglet n° " + (i + 1);
                        String message = "Contenu du panneau N°" + (i + 1);

                        PanelTabbed.getTab().removeTabAt(i);
                        PanelTabbed.getTab().insertTab(title, icon, updateComponent(color, title, message), null, i);
                        PanelTabbed.getTab().revalidate();
                        PanelTabbed.getTab().repaint();
                    }
                    // Réactualise le nombre des onglets //
                }
            }
        });

        JPanel pan = new JPanel();
        pan.add(add);
        pan.add(delete);

        this.getContentPane().add(pan, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private PanelTabbed updateComponent(Color color, String title, String message) { return new PanelTabbed(color, title, message); }
}
