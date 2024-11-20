// Importations
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    private JFrame displayZoneFrame; // Fenêtre principale du jeu
    private RenderEngine renderEngine; // Gestion du rendu graphique
    private GameEngine gameEngine; // Logique de jeu et gestion des interactions
    private PhysicEngine physicEngine; // Gestion de la physique et des collisions

    // Déclaration des timers pour contrôler les mises à jour du rendu et du jeu
    private Timer renderTimer;
    private Timer gameTimer;
    private Timer physicTimer;

    // Constructeur de la classe Main
    public Main() throws Exception {
        // Initialisation de la fenêtre principale
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600); // Définir la taille de la fenêtre
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer l'application à la fermeture de la fenêtre

        // Création du personnage principal avec une image et une position initiale
        DynamicSprite hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

        // Initialisation des moteurs de rendu, physique et de jeu
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, this); // Passer l'instance de Main pour la gestion des événements

        // Initialisation des timers pour gérer les mises à jour à intervalles réguliers
        renderTimer = new Timer(50, (time) -> renderEngine.update()); // Timer pour le rendu
        gameTimer = new Timer(50, (time) -> gameEngine.update()); // Timer pour la logique de jeu
        physicTimer = new Timer(50, (time) -> physicEngine.update()); // Timer pour les mises à jour physiques

        // Démarrer les timers pour commencer le jeu
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        // Ajouter le RenderEngine à la fenêtre pour afficher le contenu graphique
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true); // Rendre la fenêtre visible

        // Charger le niveau à partir d'un fichier et ajouter les sprites au moteur de rendu
        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList()); // Ajouter les sprites du niveau
        renderEngine.addToRenderList(hero); // Ajouter le héros au rendu
        physicEngine.addToMovingSpriteList(hero); // Ajouter le héros à la liste des sprites en mouvement
        physicEngine.setEnvironment(level.getSolidSpriteList()); // Configurer l'environnement de collision

        // Ajouter un écouteur de touches pour gérer les entrées utilisateur
        displayZoneFrame.addKeyListener(gameEngine);
    }

    // Point d'entrée principal de l'application
    public static void main(String[] args) throws Exception {
        Main main = new Main(); // Créer une instance de Main pour démarrer le jeu
    }

    // Méthode pour arrêter le jeu en arrêtant tous les timers
    public void stopGame() {
        renderTimer.stop(); // Arrêter le timer de rendu
        gameTimer.stop();   // Arrêter le timer de jeu
        physicTimer.stop(); // Arrêter le timer de physique
    }
}