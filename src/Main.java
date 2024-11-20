// Importations
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    private JFrame displayZoneFrame;
    private RenderEngine renderEngine;
    private GameEngine gameEngine;
    private PhysicEngine physicEngine;

    // Déclaration des timers pour contrôler les mises à jour du rendu et du jeu
    private Timer renderTimer;
    private Timer gameTimer;
    private Timer physicTimer;

    // Constructeur de la classe Main
    public Main() throws Exception {
        // Initialisation de la fenêtre principale
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du personnage principal avec une image et une position initiale
        DynamicSprite hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

        // Initialisation des moteurs de rendu, physique et de jeu
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, this); // Passer l'instance de Main pour la gestion des événements

        // Initialisation des timers pour gérer les mises à jour à intervalles réguliers
        renderTimer = new Timer(50, (time) -> renderEngine.update());
        gameTimer = new Timer(50, (time) -> gameEngine.update());
        physicTimer = new Timer(50, (time) -> physicEngine.update());

        // Démarrer les timers pour commencer le jeu
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        // Ajouter le RenderEngine à la fenêtre pour afficher le contenu graphique
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        // Charger le niveau à partir d'un fichier et ajouter les sprites au moteur de rendu
        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        // Ajouter un écouteur de touches pour gérer les entrées utilisateur
        displayZoneFrame.addKeyListener(gameEngine);
    }

    // Point d'entrée principal de l'application
    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }

    /**
     * Method to stop the game by stopping all timers
     */

    public void stopGame() {
        renderTimer.stop();
        gameTimer.stop();
        physicTimer.stop();
    }
}