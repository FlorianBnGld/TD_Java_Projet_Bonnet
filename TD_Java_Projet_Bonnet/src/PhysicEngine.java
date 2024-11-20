import java.util.ArrayList;

// Classe PhysicEngine qui gère la physique du jeu
public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList; // Liste des sprites en mouvement
    private ArrayList<Sprite> environment; // Liste de l'environnement dans lequel les sprites évoluent

    // Constructeur de la classe PhysicEngine
    public PhysicEngine() {
        movingSpriteList = new ArrayList<>(); // Initialiser la liste des sprites en mouvement
        environment = new ArrayList<>(); // Initialiser la liste de l'environnement
    }

    // Méthode pour ajouter un sprite à la liste de l'environnement
    public void addToEnvironmentList(Sprite sprite) {
        // Vérifie si le sprite n'est pas déjà dans la liste
        if (!environment.contains(sprite)) {
            environment.add(sprite); // Ajouter le sprite à l'environnement
        }
    }

    // Méthode pour définir l'environnement avec une nouvelle liste de sprites
    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment; // Met à jour la liste de l'environnement
    }

    // Méthode pour ajouter un sprite en mouvement à la liste
    public void addToMovingSpriteList(DynamicSprite sprite) {
        // Vérifie si le sprite n'est pas déjà dans la liste
        if (!movingSpriteList.contains(sprite)) {
            movingSpriteList.add(sprite); // Ajouter le sprite à la liste des mouvements
        }
    }

    // Méthode de mise à jour pour gérer le mouvement des sprites
    @Override
    public void update() {
        // Itérer sur chaque sprite en mouvement et tenter de le déplacer
        for (DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environment); // Déplacer le sprite si possible
        }
    }
}