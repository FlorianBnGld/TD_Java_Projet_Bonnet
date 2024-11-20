import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private Direction direction = Direction.EAST; // Direction actuelle du sprite
    private double speed = 5; // Vitesse de déplacement du sprite
    private double timeBetweenFrame = 250; // Temps entre les frames d'animation
    private final int spriteSheetNumberOfColumn = 10; // Nombre de colonnes dans la feuille de sprites

    // Attributs pour la vie et l'invincibilité
    private int health = 100; // Vie actuelle du sprite
    private boolean isInvincible = false; // État d'invincibilité
    private long invincibilityStartTime; // Temps de début d'invincibilité
    private static final int INVINCIBILITY_DURATION = 2000; // Durée de l'invincibilité en millisecondes

    // Constructeur de la classe DynamicSprite
    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height); // Appel au constructeur de la classe parente
    }

    // Méthode pour gérer les collisions avec d'autres sprites solides
    public void collideWithSolidSprite(SolidSprite solidSprite) {
        if (!isInvincible) { // Vérifie si le sprite n'est pas invincible
            health -= 10; // Diminuer la vie de 10
            isInvincible = true; // Activer l'invincibilité
            invincibilityStartTime = System.currentTimeMillis(); // Enregistrer le temps de début
            System.out.println("Collision detected! Current health: " + health); // Afficher la santé actuelle
        }
    }

    // Méthode de mise à jour appelée à chaque frame
    public void update() {
        // Gérer l'état d'invincibilité
        if (isInvincible && System.currentTimeMillis() - invincibilityStartTime > INVINCIBILITY_DURATION) {
            isInvincible = false; // Désactiver l'invincibilité après la durée spécifiée
        }
    }

    // Getter pour la vie actuelle
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

    // Setter pour définir la direction du sprite
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

    // Méthode pour déplacer le sprite si possible
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move(); // Appeler la méthode de mouvement si possible
        }
    }

    // Méthode pour dessiner le sprite
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