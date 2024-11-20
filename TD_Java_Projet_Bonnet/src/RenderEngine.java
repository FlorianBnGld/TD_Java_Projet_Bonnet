import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList; // Liste des objets à afficher
    private boolean gameOver = false; // Indicateur de l'état du jeu

    // Constructeur de la classe RenderEngine
    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>(); // Initialiser la liste de rendu
    }

    // Méthode pour ajouter un objet à la liste de rendu
    public void addToRenderList(Displayable displayable) {
        // Vérifie si l'objet n'est pas déjà dans la liste
        if (!renderList.contains(displayable)) {
            renderList.add(displayable); // Ajouter l'objet à la liste
        }
    }

    // Méthode pour ajouter une liste d'objets à la liste de rendu
    public void addToRenderList(ArrayList<Displayable> displayable) {
        // Vérifie si la liste à ajouter n'est pas déjà dans la liste de rendu
        if (!renderList.contains(displayable)) {
            renderList.addAll(displayable); // Ajouter tous les objets de la liste
        }
    }

    // Méthode pour définir l'état de "Game Over"
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver; // Met à jour l'état du jeu
    }

    // Méthode de dessin appelée pour rendre le contenu à l'écran
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Appel à la méthode parente pour dessiner
        // Dessiner chaque objet dans la liste de rendu
        for (Displayable renderObject : renderList) {
            renderObject.draw(g); // Appel de la méthode draw pour chaque objet
        }

        // Vérifier si le jeu est terminé et dessiner l'écran Game Over si c'est le cas
        if (gameOver) {
            drawGameOver(g); // Appel à la méthode pour dessiner l'écran de fin de jeu
        }
    }

    // Méthode pour dessiner l'écran Game Over
    private void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK); // Définit la couleur de fond à noir
        g.fillRect(0, 0, getWidth(), getHeight()); // Remplit l'écran avec un rectangle noir
        g.setColor(Color.RED); // Définit la couleur du texte à rouge
        g.setFont(new Font("Arial", Font.BOLD, 48)); // Définit la police de caractères
        g.drawString("Game Over", getWidth() / 4, getHeight() / 2); // Affiche le texte "Game Over"
        g.setFont(new Font("Arial", Font.PLAIN, 24)); // Change la police pour le message de redémarrage
        g.drawString("Appuyez sur R pour recommencer", getWidth() / 6, getHeight() / 2 + 50); // Affiche le message de redémarrage
    }

    // Méthode de mise à jour pour rafraîchir l'affichage
    @Override
    public void update() {
        this.repaint(); // Rafraîchit l'affichage en appelant paint()
    }
}