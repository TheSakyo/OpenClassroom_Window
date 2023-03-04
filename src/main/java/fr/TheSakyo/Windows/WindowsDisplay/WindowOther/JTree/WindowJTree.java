package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.JTree;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.utils.MethodCustom;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class WindowJTree extends Window {

    private JTree tree;
    private DefaultMutableTreeNode root;

    //Notre objet modèle
    private DefaultTreeModel model;

    private final Pannel pan = new Pannel();

    private final JButton button_add = new JButton("Ajouter");
    private final JButton button_remove = new JButton("Effacer");

    public WindowJTree() {

        super(300, 300);

        //On invoque la méthode de construction de notre 'arbre' "buildTree"
         //buildTree();

        //On invoque la méthode de construction de notre 'arbre' "listRoot"
         listRoot();

         initButton();

        this.setVisible(true);

        MethodCustom.Style_OS(this);
    }


    private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node) {

        int count = 0;

        if(file.isFile())  return new DefaultMutableTreeNode(file.getName());

        else {

            File[] list = file.listFiles();
            if(list == null) return new DefaultMutableTreeNode(file.getName());

            for(File name : list) {

                count++;

                //Pas plus de 5 enfants par noeud
                if(count < 5) {

                    DefaultMutableTreeNode subNode;

                    if(name.isDirectory()) {

                        subNode = new DefaultMutableTreeNode(name.getName()+"\\");
                        node.add(this.listFile(name, subNode));

                    } else { subNode = new DefaultMutableTreeNode(name.getName()); }

                    node.add(subNode);
                }
            }
            return node;
        }
    }

    private void listRoot() {

        this.root = new DefaultMutableTreeNode();

        int count = 0;

        for(File file : File.listRoots()) {

            DefaultMutableTreeNode reader = new DefaultMutableTreeNode(file.getAbsolutePath());

            try {

                for(File name : Objects.requireNonNull(file.listFiles())) {

                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(name.getName()+"\\");
                    reader.add(this.listFile(name, node));
                }

            } catch(NullPointerException ignored) {}

            this.root.add(reader);
        }


        //Nous créons notre modèle
        this.model = new DefaultTreeModel(this.root);

        //Et nous allons écouter ce que notre modèle a à nous dire !
        this.model.addTreeModelListener(new TreeModelListener() {

            /**
             * Méthode appelée lorsqu'un noeud a changé
             */
            public void treeNodesChanged(TreeModelEvent evt) {

                System.out.println("Changement dans l'arbre");

                Object[] listNoeuds = evt.getChildren();
                int[] listIndices = evt.getChildIndices();

                for (int i = 0; i < listNoeuds.length; i++) {
                    System.out.println("Index " + listIndices[i] + ", nouvelle valeur :" + listNoeuds[i]);
                }
            }

            /**
             * Méthode appelée lorsqu'un noeud est inséré
             */
            public void treeNodesInserted(TreeModelEvent event) {
                System.out.println("Un noeud a été inséré !");
            }

            /**
             * Méthode appelée lorsqu'un noeud est supprimé
             */
            public void treeNodesRemoved(TreeModelEvent event) {
                System.out.println("Un noeud a été retiré !");
            }

            /**
             * Méthode appelée lorsque la structure d'un noeud a été modifiée
             */
            public void treeStructureChanged(TreeModelEvent event) {
                System.out.println("La structure d'un noeud a changé !");
            }
        });


        //Nous créons, avec notre hiérarchie, un arbre
        tree = new JTree(this.root);

        //Nous passons notre modèle à notre arbre
        //==> On pouvait aussi passer l'objet TreeModel au constructeur du JTree
        tree.setModel(model);
        tree.setRootVisible(false);

        tree.setRootVisible(false); //Cache le dossier racine
        tree.setEditable(true); //On rend notre arbre éditable


        tree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {

                if(tree.getLastSelectedPathComponent() != null)
                    System.out.println(MethodCustom.getAbsolutePath(e.getPath()));


                //La méthode getPath retourne un objet TreePath
                File file = new File(MethodCustom.getAbsolutePath(e.getPath()));

                pan.setText(MethodCustom.getDescriptionFile(file));
            }
        });

        //On crée un séparateur de conteneur pour réviser
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(pan), new JScrollPane(tree));

        //On place le séparateur
        split.setDividerLocation(300);

        //Que nous plaçons sur le ContentPane de notre JFrame à l'aide d'un scroll
        this.getContentPane().add(split, BorderLayout.CENTER);
    }


    private void buildTree() {

        //Création d'une racine
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Racine");

        //Nous allons ajouter des branches et des feuilles à notre racine
        for(int i = 1; i < 6; i++) {

            DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Noeud n°"+i);

            //On rajoute 4 branches
            if(i < 4) {

                DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode("Fichier enfant");
                rep.add(rep2);
            }

            //On ajoute la feuille ou la branche à la racine
            root.add(rep);
        }

        //Nous créons, avec notre hiérarchie, un arbre
        tree = new JTree(root);

        //Que nous plaçons sur le ContentPane de notre JFrame à l'aide d'un scroll
        this.getContentPane().add(new JScrollPane(tree));
    }


    private void initButton() {

        JPanel container = new JPanel();

        button_add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(tree.getLastSelectedPathComponent() != null) {

                    String nodeName = JOptionPane.showInputDialog("Saisir un nom de noeud");

                    if(nodeName != null && !nodeName.trim().equals("")) {

                        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(nodeName);

                        parentNode.add(childNode);

                        model.insertNodeInto(childNode, parentNode, parentNode.getChildCount()-1);
                        model.nodeChanged(parentNode);
                    }

                } else {

                    JOptionPane.showMessageDialog(null, "Aucune sélection !", "Erreur", JOptionPane.ERROR_MESSAGE);

                    System.out.println("Aucune sélection !");
                }
            }
        });


        button_remove.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(tree.getLastSelectedPathComponent() != null) {

                    int option = JOptionPane.showConfirmDialog(null,
                            "Voulez-vous vraiment supprimer le noeud ?",
                            "Confimation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if(option != JOptionPane.NO_OPTION &&
                       option != JOptionPane.CLOSED_OPTION) {

                        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                        model.removeNodeFromParent(parentNode);
                    }

                } else {

                    JOptionPane.showMessageDialog(null, "Aucun noeud(s) a été supprimer !", "Information", JOptionPane.INFORMATION_MESSAGE);

                    System.out.println("Aucun noeud(s) a été supprimer !");
                }

            }
        });

        container.add(button_add);
        container.add(button_remove);

        this.getContentPane().add(container, BorderLayout.SOUTH);
    }



    static class Pannel extends JPanel {

        private final String text = "Racine de l'arbre.";
        private final JTextArea jta;

        public Pannel() {

            this.jta = new JTextArea(text);
            this.setBackground(Color.WHITE);
            this.add(jta);
        }

        public void setText(String text) { this.jta.setText(text); }
    }

}
