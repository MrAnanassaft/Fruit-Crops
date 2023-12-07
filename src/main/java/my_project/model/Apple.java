package my_project.model;


import KAGO_framework.view.DrawTool;
import my_project.Config;

public class Apple extends Fruit {

    //Attribute


    public Apple(double x, double y){
        super(x,y);
        radius = 30;
        points = 1;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(255,0,0,255);
        drawTool.drawFilledCircle(x,y,radius);
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawCircle(x,y,radius);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        //TODO 01 Ein Apfel soll von oben herab fallen. Sobald er unten den Bildschirmrand berührt wird die Methode jumpBack() aufgerufen (siehe TODO 02).
    }

    public void jumpBack(){
        this.y = radius;
        this.x = Math.random()*(300-radius*2)+radius;
    }
    public void changePoints(){
        if(points > 0){
            this.points = this.points * -1;
        }
    }
    public void changePointsBack(){
        if(points < 0){
            this.points = this.points * -1;
        }
    }

    //TODO 02 Lege eine Methode jumpBack() an, die bei Aufruf das Apple-Objekt oben am oberen Bildschirmrand an einer zufälligen x-Position positioniert.
}
