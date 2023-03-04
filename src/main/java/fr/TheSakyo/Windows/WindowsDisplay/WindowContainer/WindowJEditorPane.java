package fr.TheSakyo.Windows.WindowsDisplay.WindowContainer;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class WindowJEditorPane extends Window {

    private final JEditorPane editorPane;
    private final JEditorPane view;
    private final JTabbedPane tab = new JTabbedPane();

    String TMP_Path = "src/main/resources/tmp/";

    public WindowJEditorPane() {

        super(600, 400);

        editorPane = new JEditorPane();
        editorPane.setText(" <HTML><HEAD></HEAD><BODY></BODY></HTML> ");

        view = new JEditorPane();
        view.setEditable(false);

        tab.addTab("Editeur HTML", new JScrollPane(editorPane));
        tab.addTab("Aperçu", new JScrollPane(view));

        tab.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {

                FileWriter fw;

                try {

                    fw = new FileWriter(TMP_Path + "tmp.html");
                    fw.write(editorPane.getText());
                    fw.close();

                } catch(FileNotFoundException ex) { ex.printStackTrace(); }

                catch(IOException ex) { ex.printStackTrace(); }


                try {

                    File file = new File(TMP_Path + "tmp.html");
                    view.setEditorKit(new HTMLEditorKit());
                    view.setPage(file.toURI().toURL());

                } catch(IOException ex) { ex.printStackTrace(); }
            }
        });

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                FileWriter fw;

                try {

                    fw = new FileWriter(TMP_Path + "tmp.html");
                    fw.write("");
                    fw.close();

                } catch(FileNotFoundException ex) { ex.printStackTrace(); }

                catch(IOException ex) { ex.printStackTrace(); }
            }
        });

        this.getContentPane().add(tab, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
