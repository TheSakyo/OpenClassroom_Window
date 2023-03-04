package fr.TheSakyo.Windows.WindowsDisplay.WindowInfo;

import javax.swing.*;

@SuppressWarnings("unused")
public class WindowJOptionPane {

    String InfoPath = "src/main/resources/images/infos/";

    ImageIcon img_information = new ImageIcon(InfoPath + "information.png");
    ImageIcon img_warning = new ImageIcon(InfoPath + "warning.png");
    ImageIcon img_error = new ImageIcon(InfoPath + "error.png");

    public void Messages() {

        JOptionPane.showMessageDialog(null, "Message informatif", "Information", JOptionPane.INFORMATION_MESSAGE, img_information);

        JOptionPane.showMessageDialog(null, "Message préventif", "Attention", JOptionPane.WARNING_MESSAGE, img_warning);

        JOptionPane.showMessageDialog(null, "Message d'erreur", "Erreur", JOptionPane.ERROR_MESSAGE, img_error);
    }



    public void InputTestMessage() {

        String name = JOptionPane.showInputDialog(null, "Veuillez décliner votre identité !", "Gendarmerie nationale !", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Votre nom est " + name, "Identité", JOptionPane.INFORMATION_MESSAGE, img_information);
    }




    public void InputListTestMessage(boolean defilMode) {

        String[] sexe = {"Masculin", "Féminin", "Indéterminé"};

        if(defilMode) {

            String gender = (String)JOptionPane.showInputDialog(null,
                    "Veuillez indiquer votre sexe !",
                    "Gendarmerie nationale !",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    sexe,
                    sexe[2]);
            JOptionPane.showMessageDialog(null, "Votre sexe est " + gender, "Etat civil", JOptionPane.INFORMATION_MESSAGE, img_information);

        } else {

            int rang = JOptionPane.showOptionDialog(null,
                    "Veuillez indiquer votre sexe !",
                    "Gendarmerie nationale !",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    sexe,
                    sexe[2]);

            JOptionPane.showMessageDialog(null, "Votre sexe est " + sexe[rang], "Etat civil", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
