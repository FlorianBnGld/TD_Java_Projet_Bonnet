import java.awt.*; // Importation pour les fonctionnalités graphiques

// Interface Displayable pour les objets pouvant être affichés à l'écran
public interface Displayable {
    // Méthode pour dessiner l'objet sur le graphique fourni
    public void draw(Graphics g);
}