package fr.TheSakyo.Windows.WindowsDisplay.WindowInfo.JDialog;

public class WindowJDialogInfo {

    private String name;
    private String sexe;
    private String age;
    private String hair;
    private String size;


    public WindowJDialogInfo(){}
    public WindowJDialogInfo(String name, String sexe, String age, String hair, String size) {

        this.name = name;
        this.sexe = sexe;
        this.age = age;
        this.hair = hair;
        this.size = size;
    }

    public String toString() {

        String str;

        if(this.name != null && this.sexe != null && this.size != null && this.age != null && this.hair != null){
            str = "Description de l'objet WindowJDialogInfo\n";
            str += "Nom : " + this.name + "\n";
            str += "Sexe : " + this.sexe + "\n";
            str += "Age : " + this.age + "\n";
            str += "Cheveux : " + this.hair + "\n";
            str += "Taille : " + this.size + "\n";

        } else { str = "Aucune information !"; }

        return str;
    }
}
