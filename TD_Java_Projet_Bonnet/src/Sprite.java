import java.awt.*;

// Classe Sprite qui représente un objet affichable dans le jeu
public class Sprite implements Displayable {
    protected double x; // Position X du sprite
    protected double y; // Position Y du sprite
    protected final Image image; // Image représentant le sprite
    protected final double width; // Largeur du sprite
    protected final double height; // Hauteur du sprite

    // Constructeur de la classe Sprite
    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x; // Initialiser la position X
        this.y = y; // Initialiser la position Y
        this.image = image; // Initialiser l'image
        this.width = width; // Initialiser la largeur
        this.height = height; // Initialiser la hauteur
    }

    // Méthode pour dessiner le sprite sur le graphique fourni
    @Override
    public void draw(Graphics g) {
        // Dessiner l'image à la position (x, y)
        g.drawImage(image, (int)x, (int)y, null);
    }
}