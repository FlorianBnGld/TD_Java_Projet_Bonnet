import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>(); // Liste des sprites de l'environnement

    // Constructeur qui charge l'environnement à partir d'un fichier de configuration
    public Playground(String pathName) {
        try {
            // Chargement des images pour les différents types de sprites
            final Image imageTree = ImageIO.read(new File("./img/tree.png")); // Image d'un arbre
            final Image imageGrass = ImageIO.read(new File("./img/grass.png")); // Image d'herbe
            final Image imageRock = ImageIO.read(new File("./img/rock.png")); // Image d'une roche
            final Image imageTrap = ImageIO.read(new File("./img/trap.png")); // Image d'un piège

            // Récupération des dimensions des images pour le placement
            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);
            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);
            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            // Lecture du fichier spécifié pour construire l'environnement
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line = bufferedReader.readLine(); // Lecture de la première ligne
            int lineNumber = 0; // Numéro de ligne pour le placement vertical
            int columnNumber = 0; // Numéro de colonne pour le placement horizontal

            // Traitement de chaque ligne du fichier
            while (line != null) {
                for (byte element : line.getBytes(StandardCharsets.UTF_8)) { // Itération sur chaque caractère de la ligne
                    switch (element) {
                        case 'T': // Si le caractère est 'T', ajouter un arbre
                            environment.add(new SolidSprite(columnNumber * imageTreeWidth,
                                    lineNumber * imageTreeHeight, imageTree, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ': // Si le caractère est un espace, ajouter de l'herbe
                            environment.add(new Sprite(columnNumber * imageGrassWidth,
                                    lineNumber * imageGrassHeight, imageGrass, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R': // Si le caractère est 'R', ajouter un rocher
                            environment.add(new SolidSprite(columnNumber * imageRockWidth,
                                    lineNumber * imageRockHeight, imageRock, imageRockWidth, imageRockHeight));
                            break;
                    }
                    columnNumber++; // Passer à la colonne suivante
                }
                columnNumber = 0; // Réinitialiser le numéro de colonne après chaque ligne
                lineNumber++; // Passer à la ligne suivante
                line = bufferedReader.readLine(); // Lire la ligne suivante
            }
            bufferedReader.close(); // Fermer le BufferedReader après la lecture
        } catch (Exception e) {
            e.printStackTrace(); // Afficher l'erreur si une exception est levée
        }
    }

    // Retourne une liste des sprites solides dans l'environnement
    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSpriteArrayList = new ArrayList<>(); // Liste pour les sprites solides
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) // Vérifier si le sprite est solide
                solidSpriteArrayList.add(sprite); // Ajouter à la liste des sprites solides
        }
        return solidSpriteArrayList; // Retourner la liste des sprites solides
    }

    // Retourne une liste de tous les sprites affichables dans l'environnement
    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> displayableArrayList = new ArrayList<>(); // Liste pour les objets affichables
        for (Sprite sprite : environment) {
            displayableArrayList.add((Displayable) sprite); // Ajouter chaque sprite à la liste
        }
        return displayableArrayList; // Retourner la liste des objets affichables
    }
}