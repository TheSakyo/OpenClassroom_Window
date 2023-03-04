package fr.TheSakyo.utils;

import javax.swing.*;

public class UIOptionPane {

    public static void InitLanguage() {

        UIManager.put("OptionPane.cancelButtonText", "Annuler");
        UIManager.put("OptionPane.noButtonText", "Non");
        UIManager.put("OptionPane.yesButtonText", "Oui");

    }
}
