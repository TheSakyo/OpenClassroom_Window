package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.JTree;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.utils.MethodCustom;
import fr.TheSakyo.utils.TransferHandler.TransferHandlerCustom;
import fr.TheSakyo.utils.TransferHandler.TreeTransferHandler;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;

public class WindowJTreeDemo extends Window {

    private final WindowJTreeDemo WinTreeDemo = this;

    private JTree tree;

    private final JPanel pan = new JPanel();
    private JComboBox<String> combo;


    public WindowJTreeDemo() {

        super(400, 200);

        initComponent();

        this.add(pan, BorderLayout.CENTER);
        this.add(combo, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void initComponent() {

        pan.setLayout(new GridLayout(1, 1));
        pan.setBackground(Color.WHITE);

        tree = new JTree(getModel());

        DragAndDropLabel();

        tree.setDragEnabled(true);
        tree.setTransferHandler(new TreeTransferHandler(tree, DataFlavor.stringFlavor, TransferHandler.MOVE));

        pan.add(new JScrollPane(tree));

        //Pour le choix des actions
        combo = new JComboBox<>();
        combo.addItem("USE_SELECTION");
        combo.addItem("ON");
        combo.addItem("INSERT");
        combo.addItem("ON_OR_INSERT");

        combo.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {

                String value = e.getItem().toString();

                if(value.equals("USE_SELECTION")) tree.setDropMode(DropMode.USE_SELECTION);

                if(value.equals("ON")) tree.setDropMode(DropMode.ON);

                if(value.equals("INSERT")) tree.setDropMode(DropMode.INSERT);

                if(value.equals("ON_OR_INSERT")) tree.setDropMode(DropMode.ON_OR_INSERT);

            }
        });
    }


    private void DragAndDropLabel() {

        JLabel src = new JLabel("Noeud");

        //-------------------------------------------------------------------//
        // On crée le nouvel objet pour activer le drag'n drop //
        src.setTransferHandler(new TransferHandlerCustom());

        //On spécifie au composant qu'il doit envoyer ses données via son objet TransferHandler
        src.addMouseListener(new MouseAdapter() {

            //On utilise cet événement pour que les actions soient visibles dès le clic de souris…
            public void mousePressed(MouseEvent e) { DragAndDrop(e); }


            private void DragAndDrop(InputEvent e) {

                //On récupère le JComponent
                JComponent lab = (JComponent)e.getSource();

                //Du composant, on récupère l'objet de transfert : le nôtre
                TransferHandlerCustom handle = (TransferHandlerCustom)lab.getTransferHandler();

                MethodCustom.exportDrag(WinTreeDemo, DataFlavor.stringFlavor, handle, lab, e, TransferHandler.COPY_OR_MOVE);
            }
        });
        //-------------------------------------------------------------------//

        pan.add(src);
    }


    private TreeModel getModel() {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("SDZ");

        DefaultMutableTreeNode forum = new DefaultMutableTreeNode("Forum");
        forum.add(new DefaultMutableTreeNode("C++"));
        forum.add(new DefaultMutableTreeNode("Java"));
        forum.add(new DefaultMutableTreeNode("PHP"));

        DefaultMutableTreeNode tuto = new DefaultMutableTreeNode("Tutoriel");
        tuto.add(new DefaultMutableTreeNode("Tutoriel"));
        tuto.add(new DefaultMutableTreeNode("Programmation"));
        tuto.add(new DefaultMutableTreeNode("Mapping"));

        root.add(tuto);
        root.add(forum);

        return new DefaultTreeModel(root);
    }
}
