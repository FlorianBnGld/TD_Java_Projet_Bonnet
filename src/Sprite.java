import java.awt.*;

// Classe Sprite qui représente un objet affichable dans le jeu
public class Sprite implements Displayable {
    protected double x;
    protected double y;
    protected final Image image;
    protected final double width;
    protected final double height;

    // Constructeur de la classe Sprite
    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    /**
     * Method to draw the sprite on the supplied graphic
     * @param g The <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }
}