package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Table;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Table.button.ButtonEditorJTable;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Table.button.ButtonRendererJTable;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Table.button.DeleteButtonEditorJTable;
import fr.TheSakyo.utils.MethodCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class WindowJTable extends Window {

    private final JTable table;

    Object[][] data;

    public static Map<String, Integer> Nbre = new HashMap<String, Integer>();
    public static List<String> name = new ArrayList<String>();

    private final JButton change = new JButton("Changer la taille");
    private final JButton restore = new JButton("Rétablir");
    private final String supp = "Supprimer la ligne";

    private final String One = "+/- 1";


    //Contenu de notre combo
    String[] sexe = {"Homme", "Femme", "Indéterminé"};
    JComboBox<String> combo;

    public WindowJTable() {

        super(300, 120);

        initData();

        String[] title = {"Nom", "Nombre(s)", "Ajout/Suppr Nombre(s)", "Sexe", "OK ?", "Suppression"};

        //Combo à utiliser
        combo = new JComboBox<>(sexe);


        change.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                changeSize(200, 80);
                change.setEnabled(false);
                restore.setEnabled(true);
            }
        });

        restore.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                changeSize(75, 16);
                change.setEnabled(true);
                restore.setEnabled(false);
            }
        });

        restore.setEnabled(false);

        JPanel pan = new JPanel();

        pan.add(change);
        pan.add(restore);

        ModelJTable model = new ModelJTable(data, title);

        this.table = new JTable(model);
        this.table.setRowHeight(30);

        this.table.getColumn("Nombre(s)").setCellRenderer(new ButtonRendererJTable());
        this.table.getColumn("Nombre(s)").setCellEditor(new NbreEditorJTable(new JTextField()));

        this.table.getColumn("Ajout/Suppr Nombre(s)").setCellRenderer(new ButtonRendererJTable());
        this.table.getColumn("Ajout/Suppr Nombre(s)").setCellEditor(new ButtonEditorJTable(new JTextField(), One));


        //On définit l'éditeur par défaut pour la cellule
        //en lui spécifiant quel type d'affichage prendre en compte
        this.table.getColumn("Sexe").setCellEditor(new DefaultCellEditor(combo));
        DefaultTableCellRenderer dcr = new DefaultTableCellRenderer();
        this.table.getColumn("Sexe").setCellRenderer(dcr);

        //On définit un éditeur pour la colonne "supprimer"
        this.table.getColumn("Suppression").setCellEditor(new DeleteButtonEditorJTable(new JCheckBox()));

        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        JButton add_button = new JButton("Ajouter une ligne");
        add_button.addActionListener(new MoreListener());
        this.getContentPane().add(add_button, BorderLayout.NORTH);

        this.getContentPane().add(pan, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void initData() {

        List<String> default_names = Arrays.asList("Cyann", "Barthélémy", "Bryan", "Fulbert");

        name.addAll(default_names);

        for(int i = 0; i < name.size(); i++) {

            String user = name.get(i);

            if(!Nbre.containsKey(user)) Nbre.put(user, 0);
            else Nbre.replace(user, 0);

            String Nbre_value = "(" + Nbre.get(user) + ")";

            Object[][] newValue;

            if(i == 0 || i == name.size() - 1) newValue = new Object[][]{{user, Nbre_value, One, sexe[0], Boolean.TRUE, supp}};

            else newValue = new Object[][]{{user, Nbre_value, One, sexe[0], Boolean.FALSE, supp}};

            if(i != 0) data = MethodCustom.combineArray(data, newValue);
            else data = newValue;
        }
    }


    /**
     * Change la taille d'une ligne et d'une colonne
     * J'ai mis deux boucles afin que vous puissiez voir
     * comment parcourir les colonnes et les lignes
     */
    public void changeSize(int width, int height) {

        //Nous créons un objet TableColumn afin de travailler sur notre colonne
        TableColumn col;

        for(int i = 0; i < table.getColumnCount(); i++) {

            if(i == 1) {

                //On récupère le modèle de la colonne
                col = table.getColumnModel().getColumn(i);
                //On lui affecte la nouvelle valeur
                col.setPreferredWidth(width);
            }
        }

        for(int i = 0; i < table.getRowCount(); i++) {

            //On affecte la taille de la ligne à l'indice spécifié !
            if(i == 1) table.setRowHeight(i, height);
        }
    }

    static class NbreComponent extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //Si la valeur de la cellule est un JButton, on transtype cette valeur
            if(value instanceof JButton)return (JButton) value;
            else return this;
        }
    }

    static class NbreEditorJTable extends DefaultCellEditor {

        protected JButton button;
        private boolean   isPushed;

        public NbreEditorJTable(JTextField field) {

            super(field);

            button = new JButton();
            button.setOpaque(true);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            return null;
        }
    }




    class MoreListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            int i = table.getModel().getRowCount() + 1;

            String undertermined = "Non Déterminé (" + i + ")";

            name.add(undertermined);

            if(!Nbre.containsKey(undertermined)) Nbre.put(undertermined, 0);
            else Nbre.replace(undertermined, 0);

            String Nbre_value = "(" + Nbre.get(undertermined) + ")";

            Object[] donnee = new Object[] {undertermined, Nbre_value, One, sexe[2], Boolean.FALSE, supp};


            ((ModelJTable)table.getModel()).addRow(donnee);
        }
    }
}
