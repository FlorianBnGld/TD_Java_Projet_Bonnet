import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite {
    // Constructeur de la classe SolidSprite
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height); // Appel au constructeur de la classe parente Sprite
    }

    /**
     * Method to obtain the sprite hitbox
     * @return The Sprite hitbox
     */
    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x, y, (double) width, (double) height);
    }

    /**
     * Method for checking intersection with another hitbox
     * @param hitBox The hitbox to check if you're touching each other
     * @return True of False depending on whether hitboxes touch or not
     */
    public boolean intersect(Rectangle2D.Double hitBox) {
        return this.getHitBox().intersects(hitBox);
    }
}