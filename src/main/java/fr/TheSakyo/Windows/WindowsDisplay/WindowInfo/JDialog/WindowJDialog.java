package fr.TheSakyo.Windows.WindowsDisplay.WindowInfo.JDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowJDialog extends JDialog {

    private WindowJDialogInfo WInfo = new WindowJDialogInfo();

    @SuppressWarnings("unused")
    private JLabel nameLabel, sexeLabel, hairLabel, ageLabel, sizeLabel, size2Label, icon;
    private JRadioButton slice1, slice2, slice3, slice4;
    private JComboBox<String> sexe;
    private JComboBox<String> hair;
    private JTextField name, size;

    public WindowJDialog(JFrame parent, String title, boolean modal) {

        super(parent, title, modal);
        this.setSize(550, 270);

        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        this.initComponent();
    }

    public WindowJDialogInfo showWinJDialog() {

        this.setVisible(true);
        return this.WInfo;
    }



    private void initComponent(){

        //Icône
        icon = new JLabel(new ImageIcon("images/icone.jpg"));
        JPanel panIcon = new JPanel();
        panIcon.setBackground(Color.WHITE);
        panIcon.setLayout(new BorderLayout());
        panIcon.add(icon);

        //Le Nom
        JPanel panName = new JPanel();
        panName.setBackground(Color.WHITE);
        panName.setPreferredSize(new Dimension(220, 60));

        name = new JTextField();
        name.setPreferredSize(new Dimension(100, 25));

        panName.setBorder(BorderFactory.createTitledBorder("Nom du personnage"));

        nameLabel = new JLabel("Saisir un name :");

        panName.add(nameLabel);
        panName.add(name);

        //Le Sexe
        JPanel panSexe = new JPanel();
        panSexe.setBackground(Color.WHITE);
        panSexe.setPreferredSize(new Dimension(220, 60));

        panSexe.setBorder(BorderFactory.createTitledBorder("Sexe du personnage"));

        sexe = new JComboBox<>();
        sexe.addItem("Masculin");
        sexe.addItem("Féminin");
        sexe.addItem("Indéterminé");

        sexeLabel = new JLabel("Sexe : ");

        panSexe.add(sexeLabel);
        panSexe.add(sexe);

        //L'âge
        JPanel panAge = new JPanel();

        panAge.setBackground(Color.WHITE);
        panAge.setBorder(BorderFactory.createTitledBorder("Age du personnage"));
        panAge.setPreferredSize(new Dimension(440, 60));

        slice1 = new JRadioButton("15 - 25 ans");
        slice1.setSelected(true);

        slice2 = new JRadioButton("26 - 35 ans");
        slice3 = new JRadioButton("36 - 50 ans");
        slice4 = new JRadioButton("+ de 50 ans");

        ButtonGroup bg = new ButtonGroup();

        bg.add(slice1);
        bg.add(slice2);
        bg.add(slice3);
        bg.add(slice4);

        panAge.add(slice1);
        panAge.add(slice2);
        panAge.add(slice3);
        panAge.add(slice4);

        //La Taille
        JPanel panSize = new JPanel();
        panSize.setBackground(Color.WHITE);
        panSize.setPreferredSize(new Dimension(220, 60));
        panSize.setBorder(BorderFactory.createTitledBorder("Taille du personnage"));

        sizeLabel = new JLabel("Taille : ");
        size2Label = new JLabel(" cm");

        size = new JTextField("180");
        size.setPreferredSize(new Dimension(90, 25));

        panSize.add(sizeLabel);
        panSize.add(size);
        panSize.add(size2Label);

        //La Couleur Des Cheveux
        JPanel panHair = new JPanel();
        panHair.setBackground(Color.white);
        panHair.setPreferredSize(new Dimension(220, 60));
        panHair.setBorder(BorderFactory.createTitledBorder("Couleur de cheveux du personnage"));

        hair = new JComboBox<>();
        hair.addItem("Blond");
        hair.addItem("Brun");
        hair.addItem("Roux");
        hair.addItem("Blanc");

        hairLabel = new JLabel("Cheveux");

        panHair.add(hairLabel);
        panHair.add(hair);

        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.add(panName);
        content.add(panSexe);
        content.add(panAge);
        content.add(panSize);
        content.add(panHair);

        JPanel control = new JPanel();
        JButton okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                WInfo = new WindowJDialogInfo(name.getText(), (String)sexe.getSelectedItem(), getAge(), (String)hair.getSelectedItem(), getSize());
                setVisible(false);
            }

            public String getAge() {

                return(slice1.isSelected()) ? slice1.getText() :
                      (slice2.isSelected()) ? slice2.getText() :
                      (slice3.isSelected()) ? slice3.getText() :
                      (slice4.isSelected()) ? slice4.getText() :
                      slice1.getText();
            }

            public String getSize() { return (size.getText().equals("")) ? "180" : size.getText(); }
        });

        JButton cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });

        control.add(okButton);
        control.add(cancelButton);

        this.getContentPane().add(panIcon, BorderLayout.WEST);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }
}
