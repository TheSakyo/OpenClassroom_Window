package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Table.button;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Table.WindowJTable;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditorJTable extends DefaultCellEditor {

    protected JButton button;
    private boolean   isPushed;
    private final ButtonListener bListener = new ButtonListener();

    public ButtonEditorJTable(JTextField field, String text) {

        super(field);
        field.setText(text);
        field.addActionListener(bListener);

        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(bListener);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        //On affecte le numéro de ligne au listener
        bListener.setRow(row);

        //Idem pour le numéro de colonne
        bListener.setColumn(column);

        //On passe aussi le tableau en paramètre pour des actions potentielles
        bListener.setTable(table);

        //On réaffecte le libellé au bouton
        button.setText((value == null) ? "" : value.toString());

        //On renvoie le bouton
        return button;
    }

    //Notre listener pour le bouton
    static class ButtonListener implements ActionListener {

        private int column, row;
        private JTable table;
        private JButton button;

        private int nbre;

        public void setColumn(int col) { this.column = col; }
        public void setRow(int row) { this.row = row; }
        public void setTable(JTable table) { this.table = table; }

        public JButton getButton() { return this.button; }

        public void actionPerformed(ActionEvent e) {

            String name = WindowJTable.name.get(this.row);

            if(!getOption_ADDorRemove(name)) return;

            String Number = "(" + WindowJTable.Nbre.get(name) + ")";
            String newNumber = "(" +  nbre + ")";

            String table_value = table.getModel().getValueAt(this.row, (this.column - 1)).toString();

            String newValue = table_value.replace(Number, newNumber);

            //On affecte un nouveau libellé à une celulle de la ligne
            table.getModel().setValueAt(newValue, this.row, (this.column - 1));

            WindowJTable.Nbre.replace(name, nbre);


            //Permet de dire à notre tableau qu'une valeur a changé à l'emplacement déterminé par les valeurs passées en paramètres
            ((AbstractTableModel)table.getModel()).fireTableCellUpdated(this.row, this.column - 1);
            this.button = ((JButton)e.getSource());
        }


        private boolean getOption_ADDorRemove(String name) {

            String[] choice = {"Ajouter", "Enlever", "Aucun"};

            String selected = (String)JOptionPane.showInputDialog(null,
                    "Vous voulez ajouter ou enelver 1 nombre ?",
                    "Ajouter ou Enlever !",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choice,
                    choice[2]);

            if(selected != null) {

                if(selected.equalsIgnoreCase(choice[0]))  {

                    nbre = (WindowJTable.Nbre.get(name) + 1);
                    return true;

                } else if(selected.equalsIgnoreCase(choice[1])) {

                    String error = "Vous ne pouvez pas réduire un nombre inférieur ou égal à 0";

                    if(WindowJTable.Nbre.get(name) <= 0) {

                        JOptionPane.showMessageDialog(null, error, "Erreur", JOptionPane.ERROR_MESSAGE);
                        return false;

                    } else {

                        nbre = (WindowJTable.Nbre.get(name) - 1);
                        return true;
                    }

                } else return false;
            }
            return  false;
        }
    }
}
