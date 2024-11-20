import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    private DynamicSprite hero;
    private boolean gameOver = false;
    private Main main;

    // Constructeur de la classe GameEngine
    public GameEngine(DynamicSprite hero, Main main) {
        this.hero = hero;
        this.main = main;
    }

    /**
     * Update method called at each frame to manage the hero's current state and the end of the game
     */
    @Override
    public void update() {
        hero.update();
        // Vérifier si la santé du héros est à zéro ou moins
        if (hero.getHealth() <= 0 && !gameOver) {
            gameOver = true;
            System.out.println("Game Over!");
            stopGameAndExit();
        }
    }

    // Méthode pour arrêter le jeu et fermer l'application
    private void stopGameAndExit() {
        main.stopGame();
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

    /**
     * Method for managing user input
     * @param e Keyboard event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
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