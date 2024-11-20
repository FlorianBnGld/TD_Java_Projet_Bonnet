import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite {
    // Constructeur de la classe SolidSprite
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height); // Appel au constructeur de la classe parente Sprite
    }

    // Méthode pour obtenir la zone de collision (hitbox) du sprite
    public Rectangle2D getHitBox() {
        // Crée et retourne un rectangle représentant la hitbox du sprite
        return new Rectangle2D.Double(x, y, (double) width, (double) height);
    }

    // Méthode pour vérifier l'intersection avec une autre hitbox
    public boolean intersect(Rectangle2D.Double hitBox) {
        // Vérifie si la hitbox de ce sprite intersecte celle fournie en paramètre
        return this.getHitBox().intersects(hitBox);
    }
}