package fr.TheSakyo.utils.TransferHandler;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.JTree.WindowJTree;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.JTree.WindowJTreeDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TransferHandlerCustom extends TransferHandler {

    private static SwingDragGestureRecognizer recognizer = null;

    /**
     * Méthode permettant à l'objet de savoir si les données reçues
     * via un drop sont autorisées à être importées
     * @param info
     * @return boolean
     */
    public boolean canImport(TransferHandler.TransferSupport info, DataFlavor DF) {

        //Nous contrôlons si les données reçues sont d'un type autorisé, ici String
        return info.isDataFlavorSupported(DF);
    }

    /**
     * C'est ici que l'insertion des données dans notre composant est réalisée
     * @param support
     * @return boolean
     */
    public boolean importData(TransferHandler.TransferSupport support, DataFlavor DF){

        //Nous contrôlons si les données reçues sont d'un type autorisé
        if(!canImport(support, DF)) return false;

        //On récupère notre objet Transferable, celui qui contient les données en transit
        Transferable data = support.getTransferable();
        Object obj = null;

        //Nous récupérons nos données en spécifiant ce que nous attendons
        try { obj = data.getTransferData(DF); }
         catch(UnsupportedFlavorException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace(); }


        //Via le TRansferSupport, nous pouvons récupérer notre composant
        JComponent lab = (JComponent)support.getComponent();

        //Ont lui affecte sa nouvelle valeur, s'il s'agit d'un texte
        if(DF == DataFlavor.stringFlavor) {

            if(lab instanceof JLabel LB) LB.setText((String)obj);
        }


        return true;
    }

    /**
     * Cette méthode est invoquée à la fin de l'action DROP
     * Si des actions sont à faire ensuite, c'est ici qu'il faudra coder le comportement désiré
     * @param c
     * @param t
     * @param action
     */
    protected void exportDone(JFrame JF, DataFlavor DF, JComponent c, Transferable t, int action) {

        //Une fois le drop effectué nous effaçons le contenu de notre JComponent s'il s'agit d'un texte
        if(action == MOVE)  {

            if(DF == DataFlavor.stringFlavor) {

                if(c instanceof JLabel LB) {

                    if(!(JF instanceof WindowJTree) || !(JF instanceof WindowJTreeDemo)) LB.setText("");

                    else {

                        String text = LB.getText();
                        int indice = Integer.parseInt(text.substring(text.length() - 1));
                        LB.setText(text.substring(0, text.length() - 1) + (++indice));
                    }
                }
            }
        }

    }

    /**
     * Dans cette méthode, nous allons créer l'objet utilisé par le système de drag'n drop
     * afin de faire circuler les données entre les composants
     * Vous pouvez voir qu'il s'agit d'un objet de type Transferable
     * @param c
     * @return
     */
    protected Transferable createTransferable(DataFlavor DF, JComponent c) {

        //On retourne un nouvel objet implémentant l'interface Transferable
        //StringSelection implémente cette interface, nous l'utilisons donc s'il s'agit d'un texte
        if(DF == DataFlavor.stringFlavor) {

            if(c instanceof JLabel LB) return new StringSelection(LB.getText());
        }

        return null;
    }

    /**
     * Cette méthode est utilisée afin de déterminer le comportement
     * du composant vis-à-vis du drag'n drop : nous retrouverons
     * nos variables statiques COPY, MOVE, COPY_OR_MOVE, LINK ou NONE
     * @param c
     * @return int
     */
    public int getSourceActions(JComponent c, int action) {

        if(action == LINK) return LINK;
        else if(action == COPY) return COPY;
        else if(action == MOVE) return MOVE;
        else if(action == COPY_OR_MOVE) return COPY_OR_MOVE;
        else return NONE;

    }


    public void exportAsDrag(JFrame JF, DataFlavor DF, JComponent comp, InputEvent e, int action) {

        int srcActions = getSourceActions(comp, action);

        // only mouse events supported for drag operations
        if(!(e instanceof MouseEvent)
                // only support known actions
                || !(action == COPY || action == MOVE || action == LINK)
                // only support valid source actions
                || (srcActions & action) == 0) { action = NONE; }

        if(action != NONE && !GraphicsEnvironment.isHeadless()) {

            if(recognizer == null) { recognizer = new SwingDragGestureRecognizer(new DragHandler(JF, DF)); }
            recognizer.gestured(comp, (MouseEvent)e, srcActions, action);

        } else exportDone(JF, DF, comp, null, NONE);
    }


    private static class DragHandler implements DragGestureListener, DragSourceListener {

        private final DataFlavor DF;
        private final JFrame JF;

        private boolean scrolls;

        public DragHandler(JFrame JF, DataFlavor DF) { this.DF = DF; this.JF = JF; }

        // --- Méthodes DragSourceListener  ----------------------------------- //

        /**
         * un geste de 'Drag' a été reconnu
         */
        public void dragGestureRecognized(DragGestureEvent dge) {

            JComponent c = (JComponent)dge.getComponent();
            TransferHandlerCustom th = (TransferHandlerCustom)c.getTransferHandler();

            Transferable t = th.createTransferable(DF, c);

            if(t != null) {

                scrolls = c.getAutoscrolls();
                c.setAutoscrolls(false);

                try {

                    Image im = th.getDragImage();

                    if(im == null) dge.startDrag(null, t, this);
                    else dge.startDrag(null, im, th.getDragImageOffset(), t, this);
                    return;
                } catch(RuntimeException re) { c.setAutoscrolls(scrolls); }
            }
            th.exportDone(JF, DF, c, t, NONE);
        }

        // --- Méthodes DragSourceListener  ----------------------------------- //

        /**
         * lorsque le hotspot entre dans un site de dépôt dépendant de la plate-forme
         */
        public void dragEnter(DragSourceDragEvent dsde) {}

        /**
         * lorsque le hotspot se déplace sur un site de dépôt dépendant de la plate-forme
         */
        public void dragOver(DragSourceDragEvent dsde) {}

        /**
         * lorsque le hotspot sort d'un site de dépôt dépendant d'une plateforme
         */
        public void dragExit(DragSourceEvent dsde) {}

        /**
         * à la fin de l'opération
         */
        public void dragDropEnd(DragSourceDropEvent dsde) {

            DragSourceContext dsc = dsde.getDragSourceContext();
            JComponent c = (JComponent)dsc.getComponent();
            TransferHandlerCustom th = (TransferHandlerCustom)c.getTransferHandler();

            if(dsde.getDropSuccess()) th.exportDone(JF, DF, c, dsc.getTransferable(), dsde.getDropAction());
            else th.exportDone(JF, DF, c, dsc.getTransferable(), NONE);

            c.setAutoscrolls(scrolls);
        }

        public void dropActionChanged(DragSourceDragEvent dsde) {}
    }


    private static class SwingDragGestureRecognizer extends DragGestureRecognizer {

        SwingDragGestureRecognizer(DragGestureListener dgl) { super(DragSource.getDefaultDragSource(), null, NONE, dgl); }

        void gestured(JComponent c, MouseEvent e, int srcActions, int action) {

            setComponent(c);
            setSourceActions(srcActions);
            appendEvent(e);
            fireDragGestureRecognized(action, e.getPoint());
        }

        /**
         * enregistre les évènements de ce DragGestureRecognizer avec le composant
         */
        protected void registerListeners() {}

        /**
         * désenregistre les évènements de ce DragGestureRecognizer avec le composant
         *
         * Les sous-classes doivent surcharger cette méthode
         */
        protected void unregisterListeners() {}

    }
}