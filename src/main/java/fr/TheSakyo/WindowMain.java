package fr.TheSakyo;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.controler.AbstractControler;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.controler.CalculControler;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.model.AbstractModel;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.model.Calculator;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model.Model;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.observer.Observable;
import fr.TheSakyo.utils.UIOptionPane;

public class WindowMain {

    // -- Variables Pour 'WindowCalculator' -- //

    //Instanciation de notre modèle pour 'WindowCalculator'
    private static final AbstractModel calc = new Calculator();
    //Création du contrôleur pour 'WindowCalculator'
    private static final AbstractControler controler = new CalculControler(calc);

    // -- Variables Pour 'WindowCalculator' -- //


    //Variable Utile Pour Le Jeu 'WindowHangmanGame'
    private static final Observable modelHangman = new Model();




    public static void main(String[] args) {

        //*-* Paramétrage *-*//
        UIOptionPane.InitLanguage();
        //*-* Paramétrage *-*//



        /*** WindowContainer ***/

        /*new WindowJSplitPane();*/
        /*new WindowJScrollPane();*/
        /*new WindowJTabbedPane();*/
        /*new WindowJDesktopPane();*/
        /*new WindowJWindow();*/
        /*new WindowJEditorPane();*/
        /*new WindowJSlider();*/
        /*new WindowJProgressBar();*/

        /*** WindowContainer ***/




        /*** WindowInfo ***/

        /*new WindowJOptionPane().Messages();*/
        /*new WindowJOptionPane().InputTestMessage();*/
        /*new WindowJOptionPane().InputListTestMessage(false);*/

        /*new WindowExecJDialog();*/

        /*** WindowInfo ***/



        /*** WindowLayout ***/

        /*new WindowBoderLayout();*/
        /*new WindowGridLayout();*/
        /*new WindowBoxLayout(false, false);*/
        /*new WindowCardLayout();*/
        /*new WindowGridBagLayout();*/

        /*** WindowLayout ***/



        /*** WindowObserver ***/

        /*new WindowClock();*/
        /*new WindowJComboBox();*/
        /*new WindowJCheckBox();*/
        /*new WindowJRadioBox();*/
        /*new WindowJTextField();*/
        /*new WindowJFormattedTextField();*/


        /*** WindowObserver ***/



        /*** WindowOther ***/

        // -- Animation -- //

        /*new WindowAnimation1_0();*/
        /*new WindowAnimation2_0();*/

        // -- Animation -- //


        //new WindowMagicTable();


        /*new WindowButton(false);*/
        /*new WindowMenu();*/
        /*new WindowBorder();*/
        /*new WindowJTreeDemo();*/
        /*new WindowJTree();*/
        /*new WindowJTable();*/
        /*new WindowHangmanGame(modelHangman);*/
        /*new WindowDragAndDrop();*/
        /*new WindowGlass();*/

        // -- Partie Calculatrice -- //

        /*//Création de notre fenêtre avec le contrôleur en paramètre
        WindowCalculator WinCalcul = new WindowCalculator(controler);
        //Ajout de la fenêtre comme observer de notre modèle
        calc.addObserver(WinCalcul);*/

        // -- Partie Calculatrice -- //


        /*** WindowOther ***/



        ///***/// Other Test ///***///

        // -- Threads -- //

        //**// 1er Example //**//
        /*TestThread t = new TestThread("A");
        TestThread t2 = new TestThread("B", t);

        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("statut du thread " + t.getName() + " = " + t.getState());
        System.out.println("statut du thread " + t2.getName() + " = " + t2.getState());*/
        //**// 1er Example //**//


        //**// 2ème Example //**//
        /*
        AccBank ab1 = new AccBank();
        AccBank ab2 = new AccBank();

        Thread t1 = new Thread(new RunImpl(ab1, "Cysboy"));
        Thread t2 = new Thread(new RunImpl(ab2, "Zéro"));

        t1.start();
        t2.start();
        */
        //**// 2ème Example //**//



        //**// 3ème Example //**//

        //new TestThread().WindowThread();

        //**// 3ème Example //**//

        // -- Threads -- //



        // -- ScanFolders (ForkJoin) -- //
        /*
        Path chemin = Paths.get("C:\\Users\\sacha\\Documents");
        String filtre = "*.txt";

        //Création de notre tâche principale qui se charge de découper son travail en sous-tâches
        FolderScan fs = new FolderScan(chemin, filtre);

        //Nous récupérons le nombre de processeurs disponibles
        int processeurs = Runtime.getRuntime().availableProcessors();


        //Nous créons notre pool de thread pour nos tâches de fond
        ForkJoinPool pool = new ForkJoinPool(processeurs);
        Long start = System.currentTimeMillis();

        //Nous lançons le traitement de notre tâche principale via le pool
        pool.invoke(fs);

        Long end = System.currentTimeMillis();
        System.out.println("Il y a " + fs.getResult() + " fichier(s) portant l'extension " + filtre);
        System.out.println("Temps de traitement : " + (end - start));
        */
        // -- ScanFolders (ForkJoin) -- //


        // -- Calcul (ForkJoin) -- //
        /*
        ForkJoinPool pool = new ForkJoinPool();
        CalculMore calcul = new CalculMore(0, 100_000);

        pool.invoke(calcul);
        System.out.println("Résultat du calcul : " + calcul.getResult());
         */
        // -- Calcul (ForkJoin) -- //


        ///***/// Other Test ///***///
    }
}
