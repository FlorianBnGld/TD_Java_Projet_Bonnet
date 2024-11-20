import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    private DynamicSprite hero; // Référence au héros du jeu
    private boolean gameOver = false; // Indicateur de l'état du jeu
    private Main main; // Référence à la classe Main pour contrôler l'application

    // Constructeur de la classe GameEngine
    public GameEngine(DynamicSprite hero, Main main) {
        this.hero = hero; // Initialiser le héros
        this.main = main; // Initialiser la référence à Main
    }

    // Méthode de mise à jour appelée à chaque frame
    @Override
    public void update() {
        hero.update(); // Met à jour l'état du héros
        // Vérifier si la santé du héros est à zéro ou moins
        if (hero.getHealth() <= 0 && !gameOver) {
            gameOver = true; // Mettre à jour l'état du jeu à "terminé"
            System.out.println("Game Over!"); // Afficher le message de fin de jeu
            stopGameAndExit(); // Appeler la méthode pour arrêter le jeu et fermer la fenêtre
        }
    }

    // Méthode pour arrêter le jeu et fermer l'application
    private void stopGameAndExit() {
        main.stopGame(); // Appeler la méthode stopGame sur l'instance de Main
        // Utiliser SwingUtilities pour exécuter le code dans le thread de l'interface utilisateur
        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(1000); // Pause de 1 seconde pour permettre à l'utilisateur de voir le message
            } catch (InterruptedException e) {
                e.printStackTrace(); // Gérer les interruptions
            }
            System.exit(0); // Ferme l'application
        });
    }

    // Méthodes de l'interface KeyListener
    @Override
    public void keyTyped(KeyEvent e) {} // Méthode non utilisée

    // Méthode appelée lors de l'appui sur une touche
    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) { // Vérifier si le jeu n'est pas terminé
            switch(e.getKeyCode()) { // Vérifier quelle touche a été pressée
                case KeyEvent.VK_UP: // Touche flèche haut
                    hero.setDirection(Direction.NORTH); // Changer la direction du héros vers le nord
                    break;
                case KeyEvent.VK_DOWN: // Touche flèche bas
                    hero.setDirection(Direction.SOUTH); // Changer la direction du héros vers le sud
                    break;
                case KeyEvent.VK_LEFT: // Touche flèche gauche
                    hero.setDirection(Direction.WEST); // Changer la direction du héros vers l'ouest
                    break;
                case KeyEvent.VK_RIGHT: // Touche flèche droite
                    hero.setDirection(Direction.EAST); // Changer la direction du héros vers l'est
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {} // Méthode non utilisée
}