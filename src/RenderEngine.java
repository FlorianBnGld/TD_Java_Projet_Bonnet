import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList; // Liste des objets à afficher
    private boolean gameOver = false; // Indicateur de l'état du jeu

    // Constructeur de la classe RenderEngine
    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>(); // Initialiser la liste de rendu
    }

    /**
     * Method for adding an object to the rendering list
     * @param displayable The rendering list
     */
    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    /**
     * Method for adding a list of objects to the rendering list
     * @param displayable The rendering list
     */
    public void addToRenderList(ArrayList<Displayable> displayable) {
        if (!renderList.contains(displayable)) {
            renderList.addAll(displayable);
        }
    }

    /**
     * Method for defining “Game Over” status
     * @param gameOver The "Game Over" status
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Drawing method used to render content on screen
     * @param g  The <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }

        if (gameOver) {
            drawGameOver(g);
        }
    }

    // Méthode pour dessiner l'écran Game Over
    private void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("Game Over", getWidth() / 4, getHeight() / 2);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Appuyez sur R pour recommencer", getWidth() / 6, getHeight() / 2 + 50);
    }

    /**
     * Update method to refresh display
     */
    @Override
    public void update() {
        this.repaint();
    }
}