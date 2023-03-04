package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Table.button;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRendererJTable extends JButton implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {

        //On écrit dans le bouton ce que contient la cellule
        setText((value != null) ? value.toString() : "");

        //On retourne notre bouton
        return this;
    }
}
