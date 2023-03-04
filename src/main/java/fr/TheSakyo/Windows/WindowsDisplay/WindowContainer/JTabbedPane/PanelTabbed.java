package fr.TheSakyo.Windows.WindowsDisplay.WindowContainer.JTabbedPane;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PanelTabbed extends JPanel {

    private static final JTabbedPane tab = new JTabbedPane();

    public static String java_img = "src/main/resources/images/java.png";

    private static final Map<Component, Color> tab_color = new HashMap<Component, Color>();

    private Color color = Color.WHITE;
    private String message = "";
    private String title = "";

    @SuppressWarnings("unused")
    public PanelTabbed(){}
    public PanelTabbed(Color color, String title, String message) {

        this.color = color;

        if(tab_color.containsKey(this)) tab_color.replace(this, color);
        else tab_color.put(this, color);

        if(message == null) message = "Contenu du panneau N°" + (getCount());

        this.message = message;

        if(title == null) title = "Onglet n° " + (getCount());

        this.title = title;

        tab.addTab(title, new ImageIcon(java_img), this);
    }


    public void paintComponent(Graphics g) {

        g.setColor(this.color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString(this.message, 10, 20);
    }

    public static Color getBackgroundColor(Component comp) { return tab_color.get(comp); }

    public static Integer getCount() { return tab.getTabCount() + 1; }

    public static JTabbedPane getTab() { return tab; }
}
