import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private Direction direction = Direction.EAST;
    private double speed = 5;
    private double timeBetweenFrame = 250;
    private final int spriteSheetNumberOfColumn = 10;

    // Attributs pour la vie et l'invincibilité
    private int health = 100;
    private boolean isInvincible = false;
    private long invincibilityStartTime;
    private static final int INVINCIBILITY_DURATION = 2000;
    private int damage = 10;

    // Constructeur de la classe DynamicSprite
    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height); // Appel au constructeur de la classe parente
    }

    /**
     * Test and log if an interaction occur
     * @param solidSprite the solid sprite
     */
    public void collideWithSolidSprite(SolidSprite solidSprite) {
        if (!isInvincible) {
            health -= damage;
            isInvincible = true;
            invincibilityStartTime = System.currentTimeMillis();
            System.out.println("Collision detected! Current health: " + health);
        }
    }


    /**
     * Update method called at each frame to manage the invincibility
     */
    public void update() {
        // Gérer l'état d'invincibilité
        if (isInvincible && System.currentTimeMillis() - invincibilityStartTime > INVINCIBILITY_DURATION) {
            isInvincible = false; // Désactiver l'invincibilité après la durée spécifiée
        }
    }

    /**
     * Getter for the current health
     * @return the current Health
     */
    public int getHealth() {
        return health; // Retourner la santé actuelle
    }

    // Vérifie si le mouvement est possible sans collision
    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double moved = new Rectangle2D.Double(); // Rectangle pour le mouvement
        // Déterminer la nouvelle position en fonction de la direction
        switch (direction) {
            case EAST:
                moved.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:
                moved.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        // Vérifier les collisions avec d'autres sprites solides
        for (Sprite s : environment) {
            if ((s instanceof SolidSprite) && (s != this)) { // Vérifie si le sprite est solide et pas lui-même
                if (((SolidSprite) s).intersect(moved)) { // Vérifie l'intersection
                    collideWithSolidSprite((SolidSprite) s); // Gérer la collision
                    return false; // Mouvement non possible
                }
            }
        }
        return true; // Mouvement possible
    }

    /**
     * Setter to define sprite direction
     * @param direction The current direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction; // Met à jour la direction
    }

    // Méthode de mouvement du sprite
    private void move() {
        switch (direction) {
            case NORTH -> this.y -= speed; // Déplacement vers le nord
            case SOUTH -> this.y += speed; // Déplacement vers le sud
            case EAST -> this.x += speed; // Déplacement vers l'est
            case WEST -> this.x -= speed; // Déplacement vers l'ouest
        }
    }

    /**
     * Method for moving the sprite if possible
     * @param environment Table containing environment info
     */
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move(); // Appeler la méthode de mouvement si possible
        }
    }

    /**
     * Method to draw the sprite
     * @param g The <code>Graphics</code> context in which to paint
     */
    @Override
    public void draw(Graphics g) {
        // Calculer l'index de la frame d'animation
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        // Dessiner l'image du sprite à la position actuelle avec la frame appropriée
        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null);

        // Dessiner la barre de vie
        drawHealthBar(g);
    }

    // Méthode pour dessiner la barre de vie du sprite
    private void drawHealthBar(Graphics g) {
        g.setColor(Color.RED); // Couleur de la barre de vie
        // Dessiner la barre de vie au-dessus du sprite
        g.fillRect((int) x, (int) y - 10, (int) (width * (health / 100.0)), 5);
    }
}