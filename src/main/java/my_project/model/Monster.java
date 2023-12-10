package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Monster extends GraphicalObject {

    private int x, y; // Position des Monsters

    public Monster(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(DrawTool drawTool) {
        // Zeichne das Monster
        drawTool.setCurrentColor(new Color(189, 14, 14)); // Rot
        drawTool.drawCircle(x, y, 30); // Beispiel: Kreis für den Kopf
        drawTool.drawRectangle(x - 20, y, 40, 50); // Beispiel: Rechteck für den Körper
        drawTool.drawLine(x - 20, y + 50, x - 40, y + 80); // Beispiel: Linien für die Beine
        drawTool.drawLine(x + 20, y + 50, x + 40, y + 80);
    }

    @Override
    public void update(double dt) {
        // Hier könnten Aktualisierungen für das Monster stehen, wenn erforderlich
    }
}
