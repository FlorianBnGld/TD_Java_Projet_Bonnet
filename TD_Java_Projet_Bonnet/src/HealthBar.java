import javax.swing.*;
import java.awt.*;
// Classe HealthBar qui représente une barre de vie pour un joueur
public class HealthBar extends JPanel implements Displayable {
    private DynamicSprite player; // Référence au joueur dont la vie est affichée

    // Constructeur de la classe HealthBar
    public HealthBar(DynamicSprite player) {
        this.player = player; // Initialiser la référence au joueur
        setPreferredSize(new Dimension(400, 20)); // Taille de la barre de vie
    }

    // Méthode pour dessiner la barre de vie sur le composant
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Appel à la méthode parente pour effacer le composant
        // Calculer la largeur de la barre de vie en fonction de la santé du joueur
        int healthWidth = (int) ((player.getHealth() / 100.0) * getWidth());
        g.setColor(Color.RED); // Couleur de la barre de vie
        g.fillRect(0, 0, healthWidth, getHeight()); // Dessiner la barre de vie
        g.setColor(Color.BLACK); // Couleur du contour
        g.drawRect(0, 0, getWidth(), getHeight()); // Dessiner le contour de la barre
    }

    // Méthode pour dessiner la barre de vie, appelée par l'interface Displayable
    @Override
    public void draw(Graphics g) {
        paintComponent(g); // Appel à la méthode de peinture pour dessiner
    }
}