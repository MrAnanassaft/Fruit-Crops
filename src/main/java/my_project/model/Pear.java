package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;

public class Pear extends GraphicalObject {

    //Attribute
    private double speed;

    public Pear(double x, double y){
        this.x = x;
        this.y = y;
        speed = 150;
        width = 25;
        height = 35;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(0,255,0,255);
        drawTool.drawFilledRectangle(x,y,width,height);
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawRectangle(x,y,width,height);
    }

    @Override
    public void update(double dt) {
        this.y = this.y + 100 *dt;
        if(this.y >= Config.WINDOW_HEIGHT-15-this.height*2){
            jumpBack();
        }
        //TODO 03 Eine Birne soll von oben herab fallen. Sobald sie unten den Bildschirmrand berührt wird die Methode jumpBack() aufgerufen (siehe TODO 04).
    }
    public void jumpBack(){
        this.y = 0;
        this.x = Math.random()*(1000-width*2)+width;
    }

    //TODO 04 Lege eine Methode jumpBack() an, die bei Aufruf das Pear-Objekt oben am oberen Bildschirmrand an einer zufälligen x-Position positioniert.
}

