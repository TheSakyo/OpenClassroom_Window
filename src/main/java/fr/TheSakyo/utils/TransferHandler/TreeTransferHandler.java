package fr.TheSakyo.utils.TransferHandler;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TreeTransferHandler extends TransferHandler {

    private final JTree tree;
    private final DataFlavor DF;
    private final int action;

    public TreeTransferHandler(JTree tree, DataFlavor DF, int action) {

        this.tree = tree;
        this.DF = DF;
        this.action = action;
    }


    /**
     * Méthode permettant à l'objet de savoir si les données reçues
     * via un drop sont autorisées à être importées
     * @param info
     * @return boolean
     */
    public boolean canImport(TransferHandler.TransferSupport info) {

        //Nous contrôlons si les données reçues sont d'un type autorisé, ici String
        return info.isDataFlavorSupported(DF);
    }

    /**
     * C'est ici que l'insertion des données dans notre composant est réalisée
     * @param support
     * @return boolean
     */
    public boolean importData(TransferHandler.TransferSupport support){

        //Nous contrôlons si les données reçues sont d'un type autorisé
        if(!canImport(support)) return false;

        //On récupère l'endroit du drop via un objet approprié
        JTree.DropLocation dl = (JTree.DropLocation)support.getDropLocation();
        //Les informations afin de pouvoir créer un nouvel élément
        TreePath path = dl.getPath();
        int index = dl.getChildIndex();

        //On récupère notre objet Transferable, celui qui contient les données en transit
        Transferable data = support.getTransferable();
        Object obj = null;

        //Nous récupérons nos données en spécifiant ce que nous attendons
        try { obj = data.getTransferData(DF); }
        catch(UnsupportedFlavorException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace(); }

        //Ont lui affecte sa nouvelle valeur, s'il s'agit d'un texte
        if(DF == DataFlavor.stringFlavor) {

            //On peut maintenant ajouter le nœud
            DefaultMutableTreeNode further = new DefaultMutableTreeNode(obj);

            //On déduit le nœud parent via le chemin
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode)path.getLastPathComponent();

            DefaultTreeModel model = (DefaultTreeModel)this.tree.getModel();
            index = (index == -1) ? model.getChildCount(path.getLastPathComponent()) : index ;
            model.insertNodeInto(further, parent, index);

            tree.makeVisible(path.pathByAddingChild(further));
            tree.scrollPathToVisible(path);
        }

        return true;
    }

    /**
     * Cette méthode est utilisée afin de déterminer le comportement
     * du composant vis-à-vis du drag'n drop : nous retrouverons
     * nos variables statiques COPY, MOVE, COPY_OR_MOVE, LINK ou NONE
     * @param c
     * @return int
     */
    public int getSourceActions(JComponent c) {

        if(action == LINK) return LINK;
        else if(action == COPY) return COPY;
        else if(action == MOVE) return MOVE;
        else if(action == COPY_OR_MOVE) return COPY_OR_MOVE;
        else return NONE;

    }
}