package fr.TheSakyo.ForkJoin.ScanFolders;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderScan extends RecursiveTask<Long> {

    private final Path path;
    private final String filter;
    private long result = 0;

    //List d'objet qui contiendra les sous-tâches créées et lancées
    List<FolderScan> list = new ArrayList<>();

    public FolderScan(Path p, String f) { this.path = p; this.filter = f; }

    /**
     * Méthode qui se charge de scanner les dossiers de façon récursive
     * @throws ScanExcept
     * @throws IOException
     */
    public long sequentialScan() throws ScanExcept, IOException {

        //Si le chemin n'est pas valide, on lève une exception
        if(this.path == null || this.path.equals("")) throw new ScanExcept("Chemin à scanner non valide (vide ou null) !");

        System.out.println("Scan du dossier : " + this.path + " à la recherche des fichiers portant l'extension " + this.filter);

        DirectoryStream_SS(this.path);

        //Maintenant, on filtre le contenu de ce même dossier sur le filtre défini
        try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, filter)) {

            //Pour chaque fichier correspondant, on incrémente notre compteur
            for(Path name : listing) {

                if(Files.isReadable(name)) { result++; }

                else { System.out.println("Impossible de lire le chemin : " + name.toAbsolutePath()); }
            }
        }

        return result;
    }


    /**
     * Méthode que nous allons utiliser pour les traitements
     * en mode parallèle.
     * @throws ScanExcept
     * @throws IOException
     */
    public long parallelScan() throws ScanExcept, IOException {

        //Si le chemin n'est pas valide
        if(path == null || path.equals("")) throw new ScanExcept("Chemin à scanner non valide (vide ou null) !");

        System.out.println("Scan du dossier : " + path + " a la recherche des fichiers portant l'extension " + this.filter);

        DirectoryStream_PS(this.path);

        //On compte maintenant les fichiers, correspondant au filtre, présents dans ce dossier
        try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, this.filter)){
            for(Path ignored : listing) { result++; }
        } catch (IOException e) { e.printStackTrace(); }

        //Et, enfin, nous récupérons le résultat de toutes les tâches de fond
        for(FolderScan f : list) result += f.join();

        //Nous renvoyons le résultat final
        return result;
    }


    private void DirectoryStream_PS(Path path) throws IOException {

        //Nous listons, comme précédemment, le contenu du répertoire
        try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)) {

            //On parcourt le contenu
            for(Path name : listing) {

                //S'il s'agit d'un dossier, on crée une sous-tâche
                if(Files.isDirectory(name.toAbsolutePath())) {

                    //Nous créons donc un nouvel objet FolderScanner
                    //Qui se chargera de scanner ce dossier
                    FolderScan f = new FolderScan(name.toAbsolutePath(), this.filter);

                    //Nous l'ajoutons à la liste des tâches en cours pour récupérer le résultat plus tard
                    list.add(f);

                    //C'est cette instruction qui lance l'action en tâche de fond
                    f.fork();

                } else { System.out.println("Impossible de lire le chemin : " + name.toAbsolutePath()); }
            }
        }
    }



    private void DirectoryStream_SS(Path path) throws IOException {

        //On liste maintenant le contenu du répertoire pour traiter les sous-dossiers
        try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)) {

            for(Path name : listing) {

                try {

                    if(Files.isReadable(name)) {

                        //S'il s'agit d'un dossier, on le scanne grâce à notre objet
                        if (Files.isDirectory(name.toAbsolutePath())) {

                            FolderScan f = new FolderScan(name.toAbsolutePath(), this.filter);
                            result += f.sequentialScan();
                        }

                    } else { System.out.println("Impossible de lire le chemin : " + name.toAbsolutePath()); }

                } catch(AccessDeniedException | ScanExcept e) { System.out.println("Accès Refusé pour le chemin : " + name.toAbsolutePath()); }
            }
        }
    }



    /**
     * Méthode qui défini l'action à faire
     * dans notre cas, nous lan çons le scan en mode parallèles
     */
    protected Long compute() {

        long resultat = 0;

        try { resultat = this.parallelScan();  }

        catch(ScanExcept | IOException e) { e.printStackTrace(); }

        return resultat;
    }

    @SuppressWarnings("unused")
    public long getResult() { return this.result; }
}
