package fr.TheSakyo.utils;

import fr.TheSakyo.utils.TransferHandler.TransferHandlerCustom;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodCustom {

    @SuppressWarnings("SameParameterValue")
    public static void Waiting(long millis) throws InterruptedException { Thread.sleep(millis); }

    @SuppressWarnings("SameParameterValue")
    public static void StyleLabel(JLabel label, Font font, Color colorForeground, Color colorBackground, int align) {

        //On l'applique au JLabel
        if(font != null) label.setFont(font);

        //Changement de la couleur du texte
        if(colorForeground != null) label.setForeground(colorForeground);

        //Changement de la couleur d'arrière plan
        if(colorBackground != null) label.setBackground(colorBackground);


        //On modifie l'alignement du texte grâce aux attributs statiques
        //de la classe JLabel
        label.setHorizontalAlignment(align);
    }

    public static Color getColor(String color) {

        if(color.equalsIgnoreCase("BLACK")) return Color.BLACK;
        else if(color.equalsIgnoreCase("WHITE")) return Color.WHITE;
        else if(color.equalsIgnoreCase("LIGHT_GRAY")) return Color.LIGHT_GRAY;
        else if(color.equalsIgnoreCase("GRAY")) return Color.GRAY;
        else if(color.equalsIgnoreCase("PINK")) return Color.PINK;
        else if(color.equalsIgnoreCase("ORANGE")) return Color.ORANGE;
        else if(color.equalsIgnoreCase("YELLOW")) return Color.YELLOW;
        else if(color.equalsIgnoreCase("GREEN")) return Color.GREEN;
        else if(color.equalsIgnoreCase("MAGENTA")) return Color.MAGENTA;
        else if(color.equalsIgnoreCase("RED")) return Color.RED;
        else if(color.equalsIgnoreCase("CYAN")) return Color.CYAN;
        else if(color.equalsIgnoreCase("BLUE")) return Color.BLUE;
        else return Color.DARK_GRAY;
    }

    public static String getAbsolutePath(TreePath treePath) {

        StringBuilder str = new StringBuilder();

        //On balaie le contenu de l'objet TreePath ; Si l'objet a un nom, on l'ajoute au chemin
        for(Object name : treePath.getPath()) if(name.toString() != null) str.append(name);

        return str.toString();
    }

    /**
     * Retourne une description d'un objet File
     * @param file
     * @return
     */
    public static String getDescriptionFile(File file) {

        String str = "Chemin d'accès sur le disque : \n\t" + file.getAbsolutePath() + "\n";

        str += (file.isFile()) ? "Type : Fichier.\nTaille : " + file.length() + " ko.\n" : "Type : Dossier.\n";
        str += "Droits : \n";
        str += "\t En Lecture : " + ((file.canRead()) ? "Oui" : "Non");
        str += "\n\t En Écriture : " + ((file.canWrite()) ? "Oui" : "Non");

        return str;
    }


    public static void Style_OS(Component comp) {

        try {
            //On force à utiliser le « look and feel » du système
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            //Ici on force tous les composants de notre fenêtre (this) à se redessiner avec le « look and feel » du système
            SwingUtilities.updateComponentTreeUI(comp);
        }
        catch(InstantiationException ignored) {}
        catch(ClassNotFoundException ignored) {}
        catch(UnsupportedLookAndFeelException ignored) {}
        catch(IllegalAccessException ignored) {}

    }

    public static Object[][] combineArray (Object[][] ... objects) {

        List<List<Object>> list = new ArrayList<List<Object>>();

        for(int i = 0; i < objects.length; ++i) {

            for(int i2 = 0; i2 < objects[i].length; ++i2) {

                list.addAll(List.of(Arrays.asList(objects[i][i2])));
            }
        }

        return toArray(list);
    }



    private static Object[][] toArray(List<List<Object>> list) {

        Object[][] array = new Object[list.size()][];


        for(int i = 0; i < list.size(); i++) {

            String str = String.valueOf(list.get(i).size());
            int size = Integer.parseInt(str);

            array[i] = list.get(i).toArray(new Object[size]);
        }

        return array;
    }


    @SuppressWarnings("SameParameterValue")
    public static void exportDrag(JFrame JF, DataFlavor DF, TransferHandlerCustom TF, JComponent c, InputEvent e, int action) {

        //On lui ordonne d'amorcer la procédure de drag'n drop
        TF.exportAsDrag(JF, DF, c, e, action);

        if(action == TransferHandler.COPY_OR_MOVE) {

            String[] choice_action = {"Copier", "Couper"};

            String act = (String)JOptionPane.showInputDialog(null,
                    "Choissisez l'action que vous souhaitez !",
                    "Couper ou Copier !",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choice_action,
                    choice_action[0]);

            if(act == null) return;

            if(act.equalsIgnoreCase(choice_action[0])) action = TransferHandler.COPY;
            else if(act.equalsIgnoreCase(choice_action[1])) action = TransferHandler.MOVE;
            else return;

            try { Thread.sleep(1000); }
            catch(InterruptedException ex) { ex.printStackTrace(); }

            //On lui ordonne d'amorcer la procédure de drag'n drop
            TF.exportAsDrag(JF, DF, c, e, action);
        }
    }
}
