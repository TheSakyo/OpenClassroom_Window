package fr.TheSakyo.Windows.WindowsDisplay.WindowOther;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.utils.MethodCustom;
import fr.TheSakyo.utils.TransferHandler.TransferHandlerCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WindowDragAndDrop extends Window {

    private final WindowDragAndDrop WinDragDrop = this;

    public WindowDragAndDrop() {

        super(300, 100);

        JLabel pan = new JLabel();
        pan.setLayout(new GridLayout(2,2));
        pan.setBackground(Color.WHITE);

        JLabel srcLib = new JLabel("Source de 'drag' : ", JLabel.RIGHT);
        JLabel src = new JLabel("Texte à déplacer !");

        //-------------------------------------------------------------------//
                //On crée le nouvel objet pour activer le drag'n drop //
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

                MethodCustom.exportDrag(WinDragDrop, DataFlavor.stringFlavor, handle, lab, e, TransferHandler.COPY_OR_MOVE);
            }
        });
        //-------------------------------------------------------------------//


        JLabel destLib = new JLabel("Destination de 'drag' : ", JLabel.RIGHT);
        JTextField dest = new JTextField();

        //On active le comportement par défaut de ce composant
        dest.setDragEnabled(true);

        pan.add(srcLib);
        pan.add(src);
        pan.add(destLib);
        pan.add(dest);

        setContentPane(pan);

        this.setVisible(true);
    }
}
