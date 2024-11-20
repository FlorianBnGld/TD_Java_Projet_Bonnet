import java.util.ArrayList;

// Classe PhysicEngine qui gère la physique du jeu
public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList<Sprite> environment;

    // Constructeur de la classe PhysicEngine
    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    /**
     * Method to add a sprite to the environment list
     * @param sprite The sprite to add to the environment
     */
    public void addToEnvironmentList(Sprite sprite) {
        if (!environment.contains(sprite)) {
            environment.add(sprite);
        }
    }

    /**
     * Method for defining the environment with a new sprite list
     * @param environment Table containing environment info
     */
    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    /**
     * How to add a dynamic sprite to the moving sprite list
     * @param sprite The sprite to add to the list
     */
    public void addToMovingSpriteList(DynamicSprite sprite) {
        if (!movingSpriteList.contains(sprite)) {
            movingSpriteList.add(sprite);
        }
    }

    /**
     * Update method to manage sprite movement
     */
    @Override
    public void update() {
        for (DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environment);
        }
    }
}