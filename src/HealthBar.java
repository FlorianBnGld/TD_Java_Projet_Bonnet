import javax.swing.*;
import java.awt.*;
// Classe HealthBar qui représente une barre de vie pour un joueur
public class HealthBar extends JPanel implements Displayable {
    private DynamicSprite player;

    // Constructeur de la classe HealthBar
    public HealthBar(DynamicSprite player) {
        this.player = player;
        setPreferredSize(new Dimension(400, 20));
    }

    // Méthode pour dessiner la barre de vie sur le composant
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Appel à la méthode parente pour effacer le composant
        // Calculer la largeur de la barre de vie en fonction de la santé du joueur
        int healthWidth = (int) ((player.getHealth() / 100.0) * getWidth());
        g.setColor(Color.RED);
        g.fillRect(0, 0, healthWidth, getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Method for drawing the life bar, called by the Displayable interface
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        paintComponent(g);
    }
}