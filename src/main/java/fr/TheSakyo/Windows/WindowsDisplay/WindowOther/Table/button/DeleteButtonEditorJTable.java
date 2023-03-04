package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Table.button;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Table.ModelJTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonEditorJTable extends DefaultCellEditor {

    protected JButton button;
    private final DeleteButtonListener bListener = new DeleteButtonListener();

    public DeleteButtonEditorJTable(JCheckBox checkBox) {

        //Par défaut, ce type d'objet travaille avec un JCheckBox
        super(checkBox);

        //On crée à nouveau notre bouton
        button = new JButton();
        button.setOpaque(true);

        //On lui attribue un listener
        button.addActionListener(bListener);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        //On définit le numéro de ligne à notre listener
        bListener.setRow(row);

        //On passe aussi en paramètre le tableau pour des actions potentielles
        bListener.setTable(table);

        //On réaffecte le libellé au bouton
        button.setText( (value ==null) ? "" : value.toString() );

        //On renvoie le bouton
        return button;
    }

    static class DeleteButtonListener implements ActionListener {

        private int row;
        private JTable table;

        public void setRow(int row){this.row = row;}
        public void setTable(JTable table){this.table = table;}

        public void actionPerformed(ActionEvent event) {

            if(table.getRowCount() > 0){

                //On affecte un nouveau libellé à une celulle de la ligne
                ((ModelJTable)table.getModel()).removeRow(this.row);

            }
        }
    }
}
